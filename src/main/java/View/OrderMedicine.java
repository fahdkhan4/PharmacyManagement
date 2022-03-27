package View;


import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Service.*;

public class OrderMedicine{

    private JTable order_medicine_table , userorder_table;
    private JPanel outerpanel,toppanel ;
    private JScrollPane scrollpane , userorder_Scroll;


    public OrderMedicine(){

        JFrame order_frame = new JFrame("Order Medicine");
        JLabel nameFor_Search;
        JTextField nameFor_SearchText;
        JButton buy_product,exit,find;

//                                                                              Setting panel
        outerpanel = new JPanel(new BorderLayout());
        toppanel = new JPanel(new BorderLayout());

//

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

//                                                                                          label
        JLabel labelHead = new JLabel("Order Medicine");
        labelHead.setHorizontalAlignment(JLabel.CENTER);
        labelHead.setVerticalAlignment(JLabel.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));


        Object data[][] = ProductService.getAllMedicines();
        String [] column = {"Medicine ID","Medicine Name","Medicine Varient","Medicine Price","Quantity"};

        order_medicine_table = new JTable(data,column);
        order_medicine_table.setRowHeight(order_medicine_table.getRowHeight()+10);

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

//                                                                                      Table 2 heading
        JLabel heading_Table2 = new JLabel("Cart");
        heading_Table2.setFont(new Font("TimesRoman",Font.BOLD,40));
        heading_Table2.setBounds(1100,90,100,50);
        order_frame.add(heading_Table2);
//                                                                                      Table 2 user orders
        userorder_table = new JTable(data,column);
        userorder_table.setRowHeight(userorder_table.getRowHeight()+10);
        userorder_Scroll = new JScrollPane(userorder_table);
        userorder_table.getTableHeader().setOpaque(false);
        userorder_table.getTableHeader().setForeground(Color.BLACK);
        userorder_table.getTableHeader().setBackground(Color.ORANGE);
        userorder_Scroll.setBounds(827,150,536,600);



        scrollpane = new JScrollPane(order_medicine_table);
        toppanel.add(labelHead,BorderLayout.PAGE_START);
        toppanel.add(scrollpane,BorderLayout.CENTER);
        outerpanel.add(userorder_Scroll);


        outerpanel.add(toppanel);
        order_frame.add(outerpanel);
        order_frame.pack();

//                                                                                        Selecting data

        order_medicine_table.addMouseListener(new MouseListener() {
            int row,med_idColumn,med_quantityCol,medicine_id,medicine_quantity;
            Integer user_wants_quantity;
            JOptionPane on_med_quantity;
            @Override
            public void mouseClicked(MouseEvent e) {
                row = order_medicine_table.rowAtPoint(e.getPoint());
                med_idColumn = 0;
                med_quantityCol = 4;
                if(row >= 0) {
                    medicine_id = (int) order_medicine_table.getModel().getValueAt(row, med_idColumn);
                    medicine_quantity = (int) order_medicine_table.getModel().getValueAt(row,med_quantityCol);
                    on_med_quantity = new JOptionPane("Medicine Quantity");

                    try {
                        user_wants_quantity = Integer.parseInt(on_med_quantity.showInputDialog(order_frame, "Enter Medicine Quantity", "Medicine Quantity", JOptionPane.INFORMATION_MESSAGE));
                    }catch(Exception error){
                        System.out.println(error);
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



        order_medicine_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);

//                                                                              Size of frame
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        order_frame.setVisible(true);


        working_ofExitButton(exit);
        buy_product.addActionListener(el->{
            Receipt receipt = new Receipt();
        });
        find.addActionListener(el->{
//            FindMedicine find = new FindMedicine(nameFor_SearchText.getText());
        });

    }
    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            Home home = new Home();
        });
    }


}
