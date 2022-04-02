package View;

import Model.Product;
import Service.UserCartProduct_Services;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class Receipt {

    public JFrame receipt_frame = new JFrame("Order Medicine");


    public Receipt(){

        UserCartProduct_Services service = new UserCartProduct_Services();
        JTable receipt_medicine_table;
        JScrollPane scrollpane;
        JPanel panel;
        JButton exit;

//                                                                              Setting panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        receipt_frame.getContentPane().add(panel);
//

        Object data [][] =  service.getallUserCart_Product() ;

        String [] column = {"Code","Name","Varient","Price","Quantity"};

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

        workingOfExit_Button(exit);


    }
    public void workingOfExit_Button(JButton exit){
        exit.addActionListener(el->{
            receipt_frame.dispose();
            OrderMedicine order = new OrderMedicine();
        });
    }

    }


