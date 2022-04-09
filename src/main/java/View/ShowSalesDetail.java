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


                JLabel heading = new JLabel("ORDER SALE DETAIL");
                heading.setHorizontalAlignment(JLabel.CENTER);
                heading.setVerticalAlignment(JLabel.CENTER);
                heading.setFont(new Font("TimeRomans",Font.BOLD,40));


                showOrder_frame = new JFrame();
                showOrder_frame.setTitle("Details Of Sale No "+order_id);
                showOrder_frame.add(heading, BorderLayout.PAGE_START);

                JPanel buttonpanel = new JPanel();

                exit = new JButton("Back");
                exit.setPreferredSize(new Dimension(130,50));
                exit.setBackground(Color.ORANGE);
                exit.setForeground(Color.BLACK);

                buttonpanel.add(exit);
                buttonpanel.setBounds(40,30,1000,50);
                buttonpanel.setBackground(Color.darkGray);


                Object [][] data =  details.saleDetailsBy_OrderID();
                String[] columnNames = {"BarCode","Product Name", "Varient", "Product price","Quantity", "Amount"};

                orderDetails_table = new JTable(data, columnNames);
                orderDetails_table.setBounds(30, 40, 200, 300);
                orderDetails_table.setRowHeight(  orderDetails_table.getRowHeight()+10);

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
