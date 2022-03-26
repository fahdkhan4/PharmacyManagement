package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Service.*;

public class OrderMedicine{

    private JTable order_medicine_table;
    private JPanel outerpanel,toppanel ;
    private JScrollPane scrollpane;


    public OrderMedicine(){

        JFrame order_frame = new JFrame("Order Medicine");
        JLabel nameFor_Search;
        JTextField nameFor_SearchText;
        JButton cart,exit,submit;

//                                                                              Setting panel
        outerpanel = new JPanel(new BorderLayout());
        toppanel = new JPanel(new BorderLayout());

//

        nameFor_Search = new JLabel("Medicine Name :");
        nameFor_Search.setFont(new Font("Serif",Font.BOLD,20));
        nameFor_Search.setBounds(5,10,200,40);

        nameFor_SearchText = new JTextField(SwingConstants.LEFT);
        nameFor_SearchText.setBounds(150,7,200,40);

        submit = new JButton("Find");
        submit.setBounds(350,7,100,30);
        submit.setBackground(Color.ORANGE);
        submit.setForeground(Color.BLACK);
        order_frame.add(submit);

        order_frame.add(nameFor_Search);
        order_frame.add(nameFor_SearchText);

        //                                                                              cart button

        cart = new JButton("View Cart");
        cart.setBounds(1250,3,90,50);
        cart.setBackground(Color.ORANGE);
        cart.setForeground(Color.BLACK);
        order_frame.add(cart);

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
               

        scrollpane = new JScrollPane(order_medicine_table);
        toppanel.add(labelHead,BorderLayout.PAGE_START);
        toppanel.add(scrollpane,BorderLayout.CENTER);
        toppanel.add(cart,BorderLayout.PAGE_END);
        outerpanel.add(toppanel);
        order_frame.add(outerpanel);
        order_frame.pack();

//                                                                              Selecting data
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
                    System.out.println(medicine_id);
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


        scrollpane.setBounds(10,40,500,600);
        order_medicine_table.setFillsViewportHeight(true);
        order_frame.setLocationRelativeTo(null);

//                                                                              Size of frame
        order_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        order_medicine_table.setBounds(30,40,200,300);
        order_frame.setVisible(true);

        cart.addActionListener(el->{
            Receipt receipt = new Receipt();
        });
        working_ofExitButton(exit);

        submit.addActionListener(el->{
//            FindMedicine find = new FindMedicine(nameFor_SearchText.getText());
        });

    }
    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            Home home = new Home();
        });
    }


}
