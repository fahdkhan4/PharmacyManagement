package View;

import Service.SalesRecord;
import dao.ViewSales_Dao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ViewSales {

    public JFrame viewSales_frame = new JFrame("Order Medicine");
    public static JTable viewSales_table;
    public ViewSales_Dao sales_dao = new ViewSales_Dao();
    public SalesRecord sale = new SalesRecord();
    DefaultTableModel model;

    public static String emp_name;
    public static String firstdate;
    public static String seconddate;

    public ViewSales(){
        JPanel tablepanel , butonpanel;
        JScrollPane scrollpane;

        JButton exit;
//                                                                              Setting panel

        exit = new JButton("Exit");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        viewSales_frame.add(exit);

//
        butonpanel = new JPanel();
        tablepanel = new JPanel();
        tablepanel.setLayout(new BorderLayout());

        String [] bookTitles = {"Filter By Name", "Filter By Date", "Filter By Price"};

        JComboBox<String> bookList = new JComboBox<>(bookTitles);


        JButton getInfo = new JButton("Order Details");
        getInfo.setPreferredSize(new Dimension(150,50));
        getInfo.setBackground(Color.ORANGE);
        butonpanel.add(getInfo);
        butonpanel.add(bookList);
        butonpanel.setBounds(40,30,1000,50);
        butonpanel.setBackground(Color.CYAN);

        Object data [][] = sale.viewSaleRecord();
        String [] column = {"No","Order ID", "Employee Name","Order Date"," Cost Price","Sell Price","Profit"};

        //                                                                              cart button
        model = new DefaultTableModel(data,column);
        viewSales_table = new JTable(model);

        viewSales_table.setRowHeight(viewSales_table.getRowHeight()+10);

//                                                              add action
        JTextField employeename = new JTextField();
        JTextField firstDate = new JTextField();
        JTextField secondDate = new JTextField();

        Object[] message = {"Employee Name:", employeename};
        Object[] datefilter = {
                "Date From :", firstDate,
                "Date to :", secondDate
        };

       bookList.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JComboBox booklist = (JComboBox) e.getSource();
               String s1 = (String) booklist.getSelectedItem();

               if (s1.equalsIgnoreCase("Filter By Name")) {
                   int option = JOptionPane.showConfirmDialog(null, message, "Filter By Name", JOptionPane.OK_CANCEL_OPTION);
                   if (option == JOptionPane.OK_OPTION) {

                       emp_name = employeename.getText();
                       model = (DefaultTableModel)viewSales_table.getModel();
                       model.setRowCount(0);
                       sale.filterSales_ByName();
                   }
               }
               else if(s1.equalsIgnoreCase("Filter By Date")){
                   int option = JOptionPane.showConfirmDialog(null, datefilter, "Filter By Name", JOptionPane.OK_CANCEL_OPTION);
                   if (option == JOptionPane.OK_OPTION) {

                       firstdate = firstDate.getText();
                       seconddate = secondDate.getText();

                       model = (DefaultTableModel)viewSales_table.getModel();
                       model.setRowCount(0);
                       sale.filterSales_ByDate();
                   }
               }
               else{

               }
           }
       });


//

        scrollpane = new JScrollPane(viewSales_table);
        tablepanel.setBounds(1,100,700,600);
        tablepanel.add(scrollpane);
        tablepanel.setBackground(Color.ORANGE);
        viewSales_table.setFillsViewportHeight(true);


        JLabel labelHead = new JLabel("SALES",SwingConstants.CENTER);
        labelHead.setFont(new Font("Arial",Font.TRUETYPE_FONT,40));
        viewSales_frame.add(butonpanel,BorderLayout.LINE_START);
        viewSales_frame.add(tablepanel,BorderLayout.CENTER);
        viewSales_frame.getContentPane().add(labelHead,BorderLayout.PAGE_START);


//                                                                              Size of frame
        viewSales_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        viewSales_frame.setUndecorated(true);
        viewSales_frame.setVisible(true);

        working_ofExitButton(exit);

    }
    public void working_ofExitButton(JButton exit){
        exit.addActionListener(el->{
            viewSales_frame.dispose();
            Employee_Functionality employeeFunctionality = new Employee_Functionality();
        });
    }
}
