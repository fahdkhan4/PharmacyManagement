package View;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import Model.*;
import Service.*;
import dao.*;

public class OrderMedicine {

    public JFrame order_frame = new JFrame("Order Medicine");
    public JTable order_medicine_table, userorder_table;
    private JScrollPane scrollpane, userorder_Scroll;
    JPanel outerpanel, toppanel;
    Object[][] data , getUserCart_Data;
    public Boolean orderbool = true;

    //                                                                                                      Objects of classes
    public OrderProduct_Functionality orderProduct_functionality = new OrderProduct_Functionality();
    public UserCartProduct_Services cart_service = new UserCartProduct_Services();
    public CartProduct cartProduct = new CartProduct();
    public Invoice_Dao invoice_dao = new Invoice_Dao();
    public ViewSales_Dao sales_dao = new ViewSales_Dao();
    public ProductService productService = new ProductService();
    OrderProduct_Model orderProduct_model;
    ProductFunctionality_Dao functionality_dao = new ProductFunctionality_Dao();


    private JTextField filtertxtField;
    private TableRowSorter tablesorter;
    private DefaultTableModel tablemodel , usercartTable;
    public static long barcodeSearch;


    public OrderMedicine() {


        JLabel nameFor_Search,barcodelabel, activeMember;
        JTextField  barcodetext;
        JButton buy_product, exit;

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
        filtertxtField.setBounds(150, 7, 150, 40);

        barcodelabel = new JLabel("Bar Code:");
        barcodelabel.setFont(new Font("Serif", Font.BOLD, 20));
        barcodelabel.setBounds(300, 10, 100, 40);

        barcodetext = new JTextField(15);
        barcodetext.setBounds(400, 7, 150, 40);

        order_frame.add(nameFor_Search);
        order_frame.add(filtertxtField);
        order_frame.add(barcodelabel);
        order_frame.add(barcodetext);

//                                                                                        buy_product button
        buy_product = new JButton("BUY PRODUCT");
        buy_product.setBounds(1100, 3, 140, 40);
        buy_product.setBackground(Color.ORANGE);
        buy_product.setForeground(Color.BLACK);
        order_frame.add(buy_product);

//                                                                                         exit button
        exit = new JButton("Back");
        exit.setBounds(1250, 3, 90, 40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        order_frame.add(exit);
//                                                                                          Data Binding

        data = productService.getAllMedicines();

        String[] column = {"Medicine ID", "Medicine Name", "Medicine Varient", "Medicine Price", "Quantity"};


//                                                                              filtering the table
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

        JTextField field = new JTextField();
        field.setBounds(1200, 100, 130, 40);
//        field.setText(String.valueOf("Total Price : " + showingtotalPrice() ));
        order_frame.add(field);


        order_medicine_table.addMouseListener(new MouseAdapter()
        {
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
                user_wants_quantity = null;
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

//                                                                                                update medicine quantity
                            Integer newMedicineQTY = (medicine_quantity - user_wants_quantity);
                            Product product = new Product(medicine_id, medicine_name, medicine_varient, null, medicine_price, newMedicineQTY);
                            functionality_dao.updateMedicine_Quantity(product);

//                            editing product  table

                            tablemodel = (DefaultTableModel)order_medicine_table.getModel();
                            tablemodel.setRowCount(0);
                            productService.addingData(tablemodel,order_medicine_table);

//                                                                                             produce one order of products
                            if (orderbool) {
                                orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Draft");
                                orderProduct_functionality.inserting_OrderInformation(orderProduct_model);
                                orderbool = false;
                            }

                            Double finalPriceWithQuantity = user_wants_quantity * medicine_price;
                            ProductCart_Model cart_model = new ProductCart_Model(medicine_id, medicine_name, medicine_varient,medicine_price,finalPriceWithQuantity, user_wants_quantity, DBService.orderID);
                            Boolean cartProductDuplicate = cart_service.checkDuplicateInCart(cart_model);

                            if(cartProductDuplicate){
                                ProductCart_Model updatecart = new ProductCart_Model(medicine_id,medicine_name,medicine_varient,medicine_price,UserCartProduct_Services.cartProductprice,UserCartProduct_Services.cartProductquantity,DBService.orderID);
                                cartProduct.updateCartProductQuantity(updatecart);
                            }
                            else {
                                cartProduct.inserting_cartProduct(cart_model);
                            }
//                            ....................................................................
                            field.setText(String.valueOf("Total Price : " + showingtotalPrice() ));
//                            showingtotalPrice();
                            getUserCart_Data = cart_service.getallUserCart_Product();
                            usercartTable = new DefaultTableModel(getUserCart_Data,column);
                            userorder_table = new JTable(usercartTable);

                            userorder_table.setRowHeight(userorder_table.getRowHeight() + 10);
                            userorder_Scroll = new JScrollPane(userorder_table);
                            userorder_table.getTableHeader().setOpaque(false);
                            userorder_table.getTableHeader().setForeground(Color.BLACK);
                            userorder_table.getTableHeader().setBackground(Color.ORANGE);
                            userorder_Scroll.setBounds(827, 150, 536, 600);
                            outerpanel.add(userorder_Scroll);

//                          ...................................................................
                        } else {
                            JOptionPane.showMessageDialog(order_frame, medicine_quantity + " is the maximum quantity for this medicine");
                        }
                    } else {
                        JOptionPane.showMessageDialog(order_frame, "Please enter valid quantity");
                    }
                }
            }

        });

//            getUserCart_Data = cart_service.getallUserCart_Product();
//            usercartTable = new DefaultTableModel(getUserCart_Data, column);
//            userorder_table = new JTable(usercartTable);
//
//            userorder_table.setRowHeight(userorder_table.getRowHeight() + 10);
//            userorder_Scroll = new JScrollPane(userorder_table);
//
//            userorder_table.getTableHeader().setOpaque(false);
//            userorder_table.getTableHeader().setForeground(Color.BLACK);
//            userorder_table.getTableHeader().setBackground(Color.ORANGE);
//
//            userorder_Scroll.setBounds(827, 150, 536, 600);
//            outerpanel.add(userorder_Scroll);
//

                                                                    //          barcode work
         barcodetext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (orderbool) {
                    orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Draft");
                    orderProduct_functionality.inserting_OrderInformation(orderProduct_model);
                    orderbool = false;
                }
                getBarcode_Product(barcodetext.getText());

            }
        });


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
                    cartProduct.updateQuantityOFProductOnCancelation();
                    cartProduct.delete_cartProduct();
                    orderProduct_functionality.delete_OrderInformation();
                    this.userorder_table = null;
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

            if(this.userorder_table != null) {

                this.userorder_table = null;
                order_frame.dispose();
                OrderProduct_Model orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Completed");
                orderProduct_functionality.update_OrderInformation(orderProduct_model);

//                                          sending invoice data
                Invoice invoice = new Invoice(DBService.orderID, EmployeeLogin.activeEmployee, LocalDate.now());

                invoice_dao.insertInto_InvoiceDB(invoice);
//                                          getting product and invoice data through joins
                invoice_dao.getDataOf_InvoiceLine();
//                                           inserting the data into invoice line to show it to the recept
                invoice_dao.insertingInvoiceDataIn_InvoiceLine();
//
                sales_dao.insertingSalesRecord();
                Receipt receipt = new Receipt();
            }
            else{
                JOptionPane.showMessageDialog(order_frame,"Cart is empty");
            }
        });
    }

    public void activeEmployeeName(JLabel accountHandler) {
        accountHandler.setText("Account handler : " + EmployeeLogin.activeEmployee);
        accountHandler.setForeground(Color.BLACK);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 20));
        accountHandler.setBounds(1000, 50, 300, 50);
        order_frame.add(accountHandler);
    }

    public Double showingtotalPrice() {
        Double total_amount =  cartProduct.cartProductTotalAmount();
        System.out.println(total_amount);
        if (total_amount != null || total_amount != 0.0) {
//            JTextField field = new JTextField();
//            field.setBounds(1200, 100, 130, 40);
//            field.setText(String.valueOf("Total Price : " + total_amount));
//            order_frame.add(field);
            return total_amount;
        }
        return 0.0;
    }

    public void getBarcode_Product(String barcode){
        try {
            long code = Long.parseLong(barcode);
            barcodeSearch = code;

            tablemodel = (DefaultTableModel)order_medicine_table.getModel();
            tablemodel.setRowCount(0);
            productService.getMedicineByBarcode(tablemodel,order_medicine_table);

            cartProduct.insertIntoCartBy_Barcode();

        }catch (Exception e){
            System.out.println(e);
        };
    }
}






