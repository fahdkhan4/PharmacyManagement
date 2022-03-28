package View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import Model.Product;
import Service.*;

public class OrderMedicine{

    JFrame order_frame = new JFrame("Order Medicine");;
    private JTable order_medicine_table , userorder_table;
    private JPanel outerpanel,toppanel ;
    private JScrollPane scrollpane , userorder_Scroll;
    ArrayList<Product> userProducts = new ArrayList<>() ;




    public OrderMedicine(){

        JLabel nameFor_Search;
        JTextField nameFor_SearchText;
        JButton buy_product,exit,find;
        AtomicBoolean bool1 = new AtomicBoolean(false);
        UserCartProduct_Services service = new UserCartProduct_Services();
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
        Object data[][] = productService.getAllMedicines();

        String [] column = {"Medicine ID","Medicine Name","Medicine Varient","Medicine Price","Quantity"};

        find.addActionListener(el->{
            FindMedicine medicineFind = new FindMedicine();
            medicineFind.getMedicineName(nameFor_SearchText.getText());
            Object [][] searched_data = medicineFind.findMedicine_OnSearch();
            bool1.set(true);
        });


        order_medicine_table = new JTable(data, column);
        order_medicine_table.setRowHeight(order_medicine_table.getRowHeight()+10);
//
//                                                                                      Column size
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
            int row,med_Column,med_quantityCol,medicine_id,medicine_quantity;
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
                    medicine_id = (int) order_medicine_table.getModel().getValueAt(row, med_Column);
                    medicine_name = (String) order_medicine_table.getModel().getValueAt(row,med_Column+1);
                    medicine_varient = (String) order_medicine_table.getModel().getValueAt(row,med_Column+2);
                    medicine_price = (Double) order_medicine_table.getModel().getValueAt(row,med_Column+3);

                    medicine_quantity = (int) order_medicine_table.getModel().getValueAt(row,med_quantityCol);
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
                            Object [][] getUserCart_Data = service.getallUserCart_Product(userProducts);

                            userorder_table = new JTable(getUserCart_Data,column);
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
        heading_Table2.setBounds(1100,90,100,50);
        order_frame.add(heading_Table2);

//                                                                                      Table 2 user orders
//        Object [][] getUserCart_Data = service.getallUserCart_Product(userProducts);
//        userorder_table = new JTable(getUserCart_Data,column);
//        userorder_table.setRowHeight(userorder_table.getRowHeight()+10);
//        userorder_Scroll = new JScrollPane(userorder_table);
//        userorder_table.getTableHeader().setOpaque(false);
//        userorder_table.getTableHeader().setForeground(Color.BLACK);
//        userorder_table.getTableHeader().setBackground(Color.ORANGE);
//        userorder_Scroll.setBounds(827,150,536,600);
//        outerpanel.add(userorder_Scroll);
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


        working_ofExitButton(exit);
        buy_product.addActionListener(el->{
            Receipt receipt = new Receipt();
        });


    }


    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            Home home = new Home();
        });
    }

    public static void main(String[] args) {

    }

}
