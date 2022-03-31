package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewSales {

    public ViewSales(){
        JFrame viewSales_frame = new JFrame("Order Medicine");
        JPanel panel;
        JScrollPane scrollpane;
        JTable viewSales_table;
        JButton exit;

//                                                                              Setting panel
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        viewSales_frame.getContentPane().add(panel);
//

        String data [] [] = {
                {"1","Fahd","Gulistane johor", "11","12.0","3"},
                {"2","Saad","Shahab Faisal Colony" , "11","12.0","3"}
        };

        String [] column = {"Code","Name", "Category","Varient","Price","Quantity","Action"};

        //                                                                              cart button

        exit = new JButton("Exit");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        viewSales_frame.add(exit);


//
        DefaultTableModel model = new DefaultTableModel(data,column);
        viewSales_table = new JTable();
        viewSales_table.setRowHeight(viewSales_table.getRowHeight()+10);

        viewSales_table.setModel(model);
        scrollpane = new JScrollPane(viewSales_table);
        scrollpane.setBounds(10,40,500,600);
        viewSales_table.setFillsViewportHeight(true);


        JLabel labelHead = new JLabel("SALES",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));
        viewSales_frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);
        viewSales_frame.getContentPane().add(scrollpane,BorderLayout.CENTER);


//                                                                              Size of frame
        viewSales_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewSales_frame.setUndecorated(true);
        viewSales_table.setBounds(30,40,200,300);
        viewSales_frame.setVisible(true);

        working_ofExitButton(exit);

    }
    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            Home home= new Home();
        });
    }
}
