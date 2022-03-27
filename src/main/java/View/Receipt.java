package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Receipt {

    public Receipt(){

        JFrame receipt_frame = new JFrame("Order Medicine");
        JTable receipt_medicine_table;
        JScrollPane scrollpane;
        JPanel panel;
        JButton exit;

//                                                                              Setting panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        receipt_frame.getContentPane().add(panel);
//

        String data [] [] = {
                {"1","Fahd","Gulistane johor", "11","12.0","3"},
                {"2","Saad","Shahab Faisal Colony" , "11","12.0","3"}
        };

        String [] column = {"Code","Name", "Category","Varient","Price","Quantity","Action"};


//                                                                              exit button
        exit= new JButton("Exit");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        receipt_frame.add(exit);

//
        DefaultTableModel model = new DefaultTableModel(data,column);

        receipt_medicine_table = new JTable();
        receipt_medicine_table.setRowHeight(receipt_medicine_table.getRowHeight()+10);

        receipt_medicine_table.setModel(model);
        scrollpane = new JScrollPane(receipt_medicine_table);
        scrollpane.setBounds(10,40,500,600);
        receipt_medicine_table.setFillsViewportHeight(true);


        JLabel labelHead = new JLabel("Medicine Receipt",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));

        receipt_frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
        receipt_frame.getContentPane().add(scrollpane,BorderLayout.CENTER);


//                                                                              Size of frame
        receipt_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        receipt_frame.setUndecorated(true);

        receipt_medicine_table.setBounds(30,40,200,300);
        receipt_frame.setVisible(true);

        exit.addActionListener(el->{
            OrderMedicine order = new OrderMedicine();
        });


    }

    }



