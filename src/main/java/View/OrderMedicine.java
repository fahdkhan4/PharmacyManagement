package View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;

import Model.*;
import Service.*;
import dao.*;

public class OrderMedicine {

    public JFrame order_frame = new JFrame("Order Medicine");
    public static JTable order_medicine_table, userorder_table;
    private JScrollPane scrollpane, userorder_Scroll;
    JPanel outerpanel, toppanel;
    Object[][] data, getUserCart_Data;
    public static Double total_amount;
    public Boolean orderbool = true;
    public Boolean invoiceBool = true;
    //                                                                                                      Objects of classes
    public OrderProduct_Functionality orderProduct_functionality = new OrderProduct_Functionality();
    public UserCartProduct_Services service = new UserCartProduct_Services();
    ProductFunctionality_Dao functionality_dao = new ProductFunctionality_Dao();
    public CartProduct cartProduct = new CartProduct();
    FindMedicine medFind = new FindMedicine();
    public Invoice_Dao invoice_dao = new Invoice_Dao();

    private JTextField filtertxtField;
    private TableRowSorter tablesorter;
    private TableModel tablemodel;
//

    public OrderMedicine() {

        JLabel nameFor_Search, activeMember;
        JTextField nameFor_SearchText;
        JButton buy_product, exit, find, removeFromCart;

//                                                                              Setting panel
        outerpanel = new JPanel(new BorderLayout());
        toppanel = new JPanel(new BorderLayout());

//                                                                              Main heading
        JLabel labelHead = new JLabel("Order Medicine");
        labelHead.setHorizontalAlignment(JLabel.CENTER);
        labelHead.setVerticalAlignment(JLabel.CENTER);
        labelHead.setFont(new Font("Arial", Font.TRUETYPE_FONT, 40));

//                                                                                       Table 2 heading
        JLabel heading_Table2 = new JLabel("Cart");
        heading_Table2.setFont(new Font("TimesRoman", Font.BOLD, 40));
        heading_Table2.setBounds(1050, 90, 100, 50);
        order_frame.add(heading_Table2);

//                                                                              name of active member
        activeMember = new JLabel();
        activeEmployeeName(activeMember);


        nameFor_Search = new JLabel("Medicine Name :");
        nameFor_Search.setFont(new Font("Serif", Font.BOLD, 20));
        nameFor_Search.setBounds(5, 10, 200, 40);

        filtertxtField = new JTextField(15);
        filtertxtField.setBounds(150, 7, 200, 40);


//                                                                              remove From cart
        removeFromCart = new JButton("Remove From Cart");
        removeFromCart.setBounds(850, 90, 160, 50);
        removeFromCart.setBackground(Color.ORANGE);
        removeFromCart.setForeground(Color.BLACK);
        order_frame.add(removeFromCart);


        order_frame.add(nameFor_Search);
        order_frame.add(filtertxtField);


//                                                                                        buy_product button
        buy_product = new JButton("BUY PRODUCT");
        buy_product.setBounds(1100, 3, 140, 40);
        buy_product.setBackground(Color.ORANGE);
        buy_product.setForeground(Color.BLACK);
        order_frame.add(buy_product);

//                                                                                         exit button
        exit = new JButton("Exit");
        exit.setBounds(1250, 3, 90, 40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        order_frame.add(exit);
//                                                                                          Data Binding
        ProductService productService = new ProductService();
        data = productService.getAllMedicines();
        String[] column = {"Medicine ID", "Medicine Name", "Medicine Varient", "Medicine Price", "Quantity"};

        tablemodel = new DefaultTableModel(data, column);
        tablesorter = new TableRowSorter<>(tablemodel);
        order_medicine_table = new JTable(tablemodel);
        order_medicine_table.setRowSorter(tablesorter);

//                                                                               filtering table  data
        filtertxtField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(filtertxtField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(filtertxtField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(filtertxtField.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    tablesorter.setRowFilter(null);
                } else {
                    tablesorter.setRowFilter(RowFilter.regexFilter(str));
                }
            }
        });

        order_medicine_table.addMouseListener(new MouseListener() {
            Integer row, med_Column, med_quantityCol, medicine_quantity;
            Long medicine_id;
            String medicine_name, medicine_varient;
            Double medicine_price;
            Integer user_wants_quantity;
            JOptionPane on_med_quantity;

            @Override
            public void mouseClicked(MouseEvent e) {
                row = order_medicine_table.rowAtPoint(e.getPoint());
                med_Column = 0;
                med_quantityCol = 4;
                if (row >= 0) {
                    int viewModelRow = order_medicine_table.convertRowIndexToModel(row);

                    medicine_id = (Long) order_medicine_table.getModel().getValueAt(viewModelRow, med_Column);
                    medicine_name = (String) order_medicine_table.getModel().getValueAt(viewModelRow, med_Column + 1);
                    medicine_varient = (String) order_medicine_table.getModel().getValueAt(viewModelRow, med_Column + 2);
                    medicine_price = Double.parseDouble(order_medicine_table.getModel().getValueAt(viewModelRow, med_Column + 3).toString());
                    medicine_quantity = Integer.parseInt(order_medicine_table.getModel().getValueAt(viewModelRow, med_quantityCol).toString());

                    on_med_quantity = new JOptionPane("Medicine Quantity");

                    try {
                        user_wants_quantity = Integer.parseInt(on_med_quantity.showInputDialog(order_frame, "Enter Medicine Quantity", "Medicine Quantity", JOptionPane.INFORMATION_MESSAGE));

                    } catch (Exception error) {
                        System.out.println(error);
                    }

                    if (user_wants_quantity != null) {
                        if (user_wants_quantity <= medicine_quantity) {
//                                                                                  update medicine quantity
                            Integer newMedicineQTY = medicine_quantity - user_wants_quantity;
                            Product product = new Product(medicine_id, medicine_name, medicine_varient, null, medicine_price, newMedicineQTY);
                            functionality_dao.updateMedicine_Quantity(product);

                            OrderProduct_Model orderProduct_model;
//                                                                                             produce one order of products
                            if (orderbool) {
                                orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Draft");
                                orderProduct_functionality.inserting_OrderInformation(orderProduct_model);
                                orderbool = false;
                            }
                            Double finalPriceWithQuantity = user_wants_quantity * medicine_price;
//
                            ProductCart_Model cart_model = new ProductCart_Model(medicine_id, medicine_name, medicine_varient, finalPriceWithQuantity, user_wants_quantity, DBService.orderID);
                            cartProduct.inserting_cartProduct(cart_model);

                            total_amount = service.totalMedicine_Amount();
                            showingtotalPrice();

                            Object[][] getUserCart_Data = service.getallUserCart_Product();
                            userorder_table = new JTable(getUserCart_Data, column);

                            userorder_table.setRowHeight(userorder_table.getRowHeight() + 10);
                            userorder_Scroll = new JScrollPane(userorder_table);
                            userorder_table.getTableHeader().setOpaque(false);
                            userorder_table.getTableHeader().setForeground(Color.BLACK);
                            userorder_table.getTableHeader().setBackground(Color.ORANGE);
                            userorder_Scroll.setBounds(827, 150, 536, 600);
                            outerpanel.add(userorder_Scroll);


                        } else {
                            JOptionPane.showMessageDialog(order_frame, medicine_quantity + " is the maximum quantity for this medicine");
                        }
                    } else {
                        JOptionPane.showMessageDialog(order_frame, "Please enter valid quantity");
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

//
//                userorder_table.addMouseListener(new MouseListener() {
//                    Integer orderRow, orderColumn;
//                    Long barcode;
//
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        orderRow = userorder_table.rowAtPoint(e.getPoint());
//                        orderColumn = 0;
//                        if (orderRow >= 0) {
//                            barcode = (Long) userorder_table.getModel().getValueAt(orderRow, orderColumn);
//                            int res = JOptionPane.showOptionDialog(order_frame, "Are u sure to remove product from cart", "Order", JOptionPane.DEFAULT_OPTION,
//                                    JOptionPane.INFORMATION_MESSAGE, null, null, null);
//                            if (res == 0) {
//                                removeFromCart.addActionListener(el -> {
//                                    cartProduct.removeSpecific_CartProduct(barcode);
//                                });
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void mousePressed(MouseEvent e) {
//                    }
//
//                    @Override
//                    public void mouseReleased(MouseEvent e) {
//                    }
//
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                    }
//            });
//            }


        order_medicine_table.setRowHeight(order_medicine_table.getRowHeight() + 10);
//                                                                                            Column size
        order_medicine_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int col = 0; col < order_medicine_table.getColumnCount(); col++) {
            TableColumn tableColumn = order_medicine_table.getColumnModel().getColumn(col);
            int preferredWidth = tableColumn.getMinWidth() + 150;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < order_medicine_table.getRowCount(); row++) {
                TableCellRenderer cellRenderer = order_medicine_table.getCellRenderer(row, col);
                Component c = order_medicine_table.prepareRenderer(cellRenderer, row, col);
                int width = c.getPreferredSize().width + order_medicine_table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth(preferredWidth);
        }


//
        scrollpane = new JScrollPane(order_medicine_table);
        toppanel.add(labelHead, BorderLayout.PAGE_START);
        toppanel.add(scrollpane, BorderLayout.CENTER);
//

        outerpanel.add(toppanel);
        order_frame.add(outerpanel);
        order_frame.pack();

        order_medicine_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);
//                                                                                        Size of frame
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        order_frame.setVisible(true);

        workingOf_BuyButton(buy_product);
        working_ofExitButton(exit);

    }

    public void working_ofExitButton(JButton exit) {
        exit.addActionListener(el -> {

            if (this.userorder_table != null) {
                int res = JOptionPane.showOptionDialog(order_frame, "Are you Sure \n Exit will remove the cart ", "Order", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
                if (res == 0) {
                    order_frame.dispose();
                    cartProduct.delete_cartProduct();
                    orderProduct_functionality.delete_OrderInformation();
                    Employee_Functionality employeeFunctionality = new Employee_Functionality();
                }
            } else {
                order_frame.dispose();
                Employee_Functionality employeeFunctionality = new Employee_Functionality();
            }
        });
    }


    public void workingOf_BuyButton(JButton buy_product) {
        buy_product.addActionListener(el -> {
            this.userorder_table = null;
            order_frame.dispose();
            OrderProduct_Model orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Completed");
            orderProduct_functionality.update_OrderInformation(orderProduct_model);

//                  sending invoice data
            Invoice invoice = new Invoice(DBService.orderID, EmployeeLogin.activeEmployee, LocalDate.now());
            invoice_dao.insertInto_InvoiceDB(invoice);
//                 getting product and invoice data through joins
            invoice_dao.getDataOf_InvoiceLine();
//                 inserting the data into invoice line to show it to the recept
            invoice_dao.insertingInvoiceDataIn_InvoiceLine();

//
            Receipt example = new Receipt();


        });
    }

    public void activeEmployeeName(JLabel accountHandler) {
        accountHandler.setText("Account handler : " + EmployeeLogin.activeEmployee);
        accountHandler.setForeground(Color.BLACK);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 20));
        accountHandler.setBounds(1000, 50, 300, 50);
        order_frame.add(accountHandler);
    }

    public void showingtotalPrice() {
        if (total_amount != null || total_amount != 0.0) {
            JTextField field = new JTextField();
            field.setBounds(1200, 100, 130, 40);
            field.setText(String.valueOf("Total Price : " + total_amount));
            order_frame.add(field);
        }
    }
}






