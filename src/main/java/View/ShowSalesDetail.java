package View;

import Service.SalesDetails_ShowOID;

import javax.swing.*;
import java.awt.*;

public class ShowSalesDetail {
    SalesDetails_ShowOID details = new SalesDetails_ShowOID();
    public JFrame showOrder_frame;
    JTable orderDetails_table;

    public ShowSalesDetail(Integer order_id){

               JButton exit;

                showOrder_frame = new JFrame();
                showOrder_frame.setTitle("Details Of Sale No "+order_id);

                JPanel buttonpanel = new JPanel();

                exit = new JButton("Exit");
                exit.setBounds(1250,3,90,40);
                exit.setBackground(Color.ORANGE);
                exit.setForeground(Color.BLACK);

                buttonpanel.add(exit);
                buttonpanel.setBounds(40,30,1000,50);
                buttonpanel.setBackground(Color.CYAN);


                Object [][] data =  details.saleDetailsBy_OrderID();
                String[] columnNames = {"BarCode","Product Name", "Varient", "Product price","Quantity", "Amount"};

                orderDetails_table = new JTable(data, columnNames);
                orderDetails_table.setBounds(30, 40, 200, 300);

                JScrollPane sp = new JScrollPane(orderDetails_table);
                showOrder_frame.add(buttonpanel,BorderLayout.LINE_START);
                showOrder_frame.add(sp);

                showOrder_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                showOrder_frame.setUndecorated(false);
                showOrder_frame.setVisible(true);

                workingOfExitButton(exit);

        }

    public void workingOfExitButton(JButton exit){
        exit.addActionListener(el->{
            showOrder_frame.dispose();
            ViewSales sale = new ViewSales();
        });
        }

}
