package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import Model.Product;
import Service.*;

public class OrderMedicine{

     public JFrame order_frame = new JFrame("Order Medicine");
    private JTable order_medicine_table , userorder_table;
    private JScrollPane scrollpane , userorder_Scroll;
    public ArrayList<Product> userProducts = new ArrayList<>();
    JPanel outerpanel,toppanel;
    Object [][] data;
    Double totalMedicine_amount;
    public JButton button;

    public OrderMedicine(){

        JLabel nameFor_Search;
        JTextField nameFor_SearchText;
        JButton buy_product,exit,find;
        UserCartProduct_Services service = new UserCartProduct_Services();
        FindMedicine medFind = new FindMedicine();

//                                                                              Setting panel
        outerpanel = new JPanel(new BorderLayout());
        toppanel = new JPanel(new BorderLayout());

//                                                                              Main heading
        JLabel labelHead = new JLabel("Order Medicine");
        labelHead.setHorizontalAlignment(JLabel.CENTER);
        labelHead.setVerticalAlignment(JLabel.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));


        nameFor_Search = new JLabel("Medicine Name :");
        nameFor_Search.setFont(new Font("Serif",Font.BOLD,20));
        nameFor_Search.setBounds(5,10,200,40);

        nameFor_SearchText = new JTextField(SwingConstants.LEFT);
        nameFor_SearchText.setBounds(150,7,200,40);

        find = new JButton("Find");
        find.setBounds(350,7,100,40);
        find.setBackground(Color.ORANGE);
        find.setForeground(Color.BLACK);
        order_frame.add(find);

        order_frame.add(nameFor_Search);
        order_frame.add(nameFor_SearchText);

//                                                                              buy_product button
        buy_product = new JButton("BUY PRODUCT");
        buy_product.setBounds(1100,3,140,40);
        buy_product.setBackground(Color.ORANGE);
        buy_product.setForeground(Color.BLACK);
        order_frame.add(buy_product);

//                                                                                         exit button
        exit = new JButton("Exit");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        order_frame.add(exit);
//                                                                                         Data Binding
        ProductService productService = new ProductService();
        data = productService.getAllMedicines();

        String [] column = {"Medicine ID","Medicine Name","Medicine Varient","Medicine Price","Quantity"};
        order_medicine_table = new JTable(data, column);

        find.addActionListener(el->{
            if(nameFor_SearchText.getText().isEmpty()) {
                JOptionPane.showMessageDialog(order_frame,"Invalid Search");
            }
            else{
                medFind.getMedicineName(nameFor_SearchText.getText());
                this.data = medFind.findMedicine_OnSearch();
                order_medicine_table = new JTable(data,column);
            }
        });

//                                                                                       search by medicine
        order_medicine_table.setRowHeight(order_medicine_table.getRowHeight()+10);
//                                                                                       Column size
        order_medicine_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int col = 0; col < order_medicine_table.getColumnCount(); col++)
        {
            TableColumn tableColumn = order_medicine_table.getColumnModel().getColumn(col);
            int preferredWidth = tableColumn.getMinWidth()+150;
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < order_medicine_table.getRowCount(); row++)
            {
                TableCellRenderer cellRenderer = order_medicine_table.getCellRenderer(row, col);
                Component c = order_medicine_table.prepareRenderer(cellRenderer, row, col);
                int width = c.getPreferredSize().width + order_medicine_table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                if (preferredWidth >= maxWidth)
                {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            tableColumn.setPreferredWidth( preferredWidth );
        }

        order_medicine_table.addMouseListener(new MouseListener() {
            Integer row,med_Column,med_quantityCol,medicine_quantity;
            Long medicine_id;
            String medicine_name,medicine_varient;
            Double medicine_price;
            Integer user_wants_quantity;
            JOptionPane on_med_quantity;

            @Override
            public void mouseClicked(MouseEvent e) {
                row = order_medicine_table.rowAtPoint(e.getPoint());
                med_Column = 0;
                med_quantityCol = 4;
                if(row >= 0) {
                    medicine_id = (Long) order_medicine_table.getModel().getValueAt(row, med_Column);
                    medicine_name = (String) order_medicine_table.getModel().getValueAt(row,med_Column+1);
                    medicine_varient = (String) order_medicine_table.getModel().getValueAt(row,med_Column+2);
                    try {
                        medicine_price = (Double) order_medicine_table.getModel().getValueAt(row, med_Column + 3);
                    }catch(ClassCastException err){
                        System.out.println(err);
                    }

                    medicine_quantity = Integer.parseInt(order_medicine_table.getModel().getValueAt(row,med_quantityCol).toString());

                    on_med_quantity = new JOptionPane("Medicine Quantity");

                    try {
                        user_wants_quantity = Integer.parseInt(on_med_quantity.showInputDialog(order_frame, "Enter Medicine Quantity", "Medicine Quantity", JOptionPane.INFORMATION_MESSAGE));

                    }catch(Exception error){
                        user_wants_quantity = null;
                        System.out.println(error);
                    }

                    if(user_wants_quantity != null) {
                        if (user_wants_quantity <= medicine_quantity) {

                            userProducts.add(new Product(medicine_id,medicine_name,medicine_varient,medicine_price,user_wants_quantity));

                            totalMedicine_amount = medFind.totalMedicine_Amount(medicine_price,user_wants_quantity);
                            Object [][] getUserCart_Data = service.getallUserCart_Product(userProducts);

                            userorder_table = new JTable(getUserCart_Data,column);
                            showingtotalPrice();

                            userorder_table.setRowHeight(userorder_table.getRowHeight()+10);
                            userorder_Scroll = new JScrollPane(userorder_table);
                            userorder_table.getTableHeader().setOpaque(false);
                            userorder_table.getTableHeader().setForeground(Color.BLACK);
                            userorder_table.getTableHeader().setBackground(Color.ORANGE);
                            userorder_Scroll.setBounds(827,150,536,600);

                            outerpanel.add(userorder_Scroll);

                        } else {
                            JOptionPane.showMessageDialog(order_frame, medicine_quantity + " is the maximum quantity for this medicine");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(order_frame,"Please enter valid quantity");
                    }

                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });

//                                                                                      Table 2 heading
        JLabel heading_Table2 = new JLabel("Cart");
        heading_Table2.setFont(new Font("TimesRoman",Font.BOLD,40));
        heading_Table2.setBounds(1050,90,100,50);
        order_frame.add(heading_Table2);
//
        scrollpane = new JScrollPane(order_medicine_table);
        toppanel.add(labelHead,BorderLayout.PAGE_START);
        toppanel.add(scrollpane,BorderLayout.CENTER);
//

        outerpanel.add(toppanel);
        order_frame.add(outerpanel);
        order_frame.pack();
//                                                                                        Selecting data
        order_medicine_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);
//                                                                                        Size of frame
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        order_frame.setVisible(true);

        workingOf_BuyButton(buy_product);
        working_ofExitButton(exit);

    }

    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            order_frame.dispose();
            Home home = new Home();
        });
    }

    public void workingOf_BuyButton(JButton buy_product){
        buy_product.addActionListener(el->{
            order_frame.dispose();
            Receipt receipt = new Receipt(userProducts);
        });
    }

    public void showingtotalPrice(){
        System.out.println(totalMedicine_amount);
        if(totalMedicine_amount != 0) {
            JTextField field  = new JTextField();
            field.setBounds(1200,100,130,40);
            field.setText(String.valueOf("Total Price : "+totalMedicine_amount));
            order_frame.add(field);
        }
    }



}
