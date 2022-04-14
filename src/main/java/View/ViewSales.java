package View;

import Service.SalesRecord;
import View.Admin.Admin_ShowSalesDetails;
import dao.ViewSales_Dao;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ViewSales {

    public JFrame viewSales_frame = new JFrame("Order Medicine");
    public JTable viewSales_table;
    public ViewSales_Dao sales_dao = new ViewSales_Dao();
    public SalesRecord sale = new SalesRecord();
    DefaultTableModel model;

    public static Integer SalesDetails_orderId;
    public static String emp_name;
    public static LocalDate firstdate;
    public static LocalDate seconddate;
    Double amountRevenue;

    public ViewSales(){

        JPanel tablepanel , butonpanel;
        JScrollPane scrollpane;
        JTextField totalAmount;
        JButton exit;

        JTextField filteringDetail_1 = new JTextField();
        filteringDetail_1.setEditable(false);
        filteringDetail_1.setVisible(false);
        filteringDetail_1.setForeground(Color.BLACK);
        filteringDetail_1.setBackground(Color.ORANGE);
        filteringDetail_1.setFont(new Font("TimesRoman",Font.BOLD,15));
        filteringDetail_1.setBounds(100,3,300,40);
        viewSales_frame.add(filteringDetail_1);


        totalAmount = new JTextField();
        totalAmount.setEditable(false);
        amountRevenue = sale.totalRevenue();
        totalAmount.setText("     Revenue : "+amountRevenue);
        totalAmount.setFont(new Font("TimesRoman",Font.BOLD,15));
        totalAmount.setBackground(Color.ORANGE);
        totalAmount.setForeground(Color.BLACK);
        totalAmount.setBounds(1000,3,200,40);
        viewSales_frame.add(totalAmount);
//                                                                              Setting panel

        exit = new JButton("Back");
        exit.setBounds(1250,3,90,40);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);
        viewSales_frame.add(exit);

//
        butonpanel = new JPanel();
        tablepanel = new JPanel();
        tablepanel.setLayout(new BorderLayout());

        String [] filteringOptions = {"Filter By Name", "Filter By Date", "Filter By profit" ," All sales"};

        JComboBox<String> filteringCheckbox = new JComboBox<>(filteringOptions);


        butonpanel.add(filteringCheckbox);
        butonpanel.setBounds(40,30,1000,50);
        butonpanel.setBackground(Color.darkGray);

        Object data [][] = sale.viewSaleRecord();
        String [] column = {"No","Order ID", "Employee Name","Order Date"," Cost Price","Sell Price","Profit"};

        //                                                                              cart button
        model = new DefaultTableModel(data,column);
        viewSales_table = new JTable(model);
        viewSales_table.setRowHeight(viewSales_table.getRowHeight()+10);

        viewSales_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                workingOnMouse(e);
            }
        });

//                                                              add action on combo box
        JTextField employeename = new JTextField();
        JTextField firstDate = new JTextField();
        JTextField secondDate = new JTextField();


        Object[] filterName = {"Employee Name:", employeename};
        Object[] datefilter = {"Date From :", firstDate, "Date to :", secondDate};

       filteringCheckbox.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               JComboBox booklist = (JComboBox) e.getSource();
               String s1 = (String) booklist.getSelectedItem();

               if (s1.equalsIgnoreCase("Filter By Name")) {
                   int option = JOptionPane.showConfirmDialog(null, filterName, "Filter By Name", JOptionPane.OK_CANCEL_OPTION);
                   if (option == JOptionPane.OK_OPTION) {

                       filteringDetail_1.setText("Filtered by name : "+employeename.getText());
                       filteringDetail_1.setVisible(true);

                       emp_name = employeename.getText();
                       model = (DefaultTableModel)viewSales_table.getModel();
                       model.setRowCount(0);
                       sale.filterSales_ByName(model,viewSales_table);
                   }
               }
               else if(s1.equalsIgnoreCase("Filtered By Date")){

                   firstDate.setText("2022-04-06");
                   secondDate.setText("2022-04-06");

                   int option = JOptionPane.showConfirmDialog(null, datefilter, "Filter By Name", JOptionPane.OK_CANCEL_OPTION);
                   if (option == JOptionPane.OK_OPTION) {

                       try {
                           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
                           firstdate =  LocalDate.parse(firstDate.getText(),formatter);
                           seconddate = LocalDate.parse(secondDate.getText(),formatter);

                           filteringDetail_1.setText("Date from : "+firstDate.getText()+" To "+secondDate.getText());
                           filteringDetail_1.setVisible(true);



                       }catch (Exception error){
                           System.out.println(error);
                       }

                       model = (DefaultTableModel)viewSales_table.getModel();
                       model.setRowCount(0);
                       sale.filterSales_ByDate(model,viewSales_table);
                   }
               }
               else if (s1.equalsIgnoreCase("Filter By profit")){
                   filteringDetail_1.setVisible(false);
                   model = (DefaultTableModel)viewSales_table.getModel();
                   model.setRowCount(0);
                   sale.SortingBy_Profit(model,viewSales_table);
               }
               else{
                   filteringDetail_1.setVisible(false);
                   model = (DefaultTableModel)viewSales_table.getModel();
                   model.setRowCount(0);
                   sale.viewAllSaleRecord(model,viewSales_table);
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
    public void workingOnMouse(MouseEvent e){
        try {
            Integer sale_row, sale_column;
            sale_row = viewSales_table.rowAtPoint(e.getPoint());
            sale_column = 1;
            if(sale_row >= 0) {
                SalesDetails_orderId = (Integer) viewSales_table.getModel().getValueAt(sale_row, sale_column);
                viewSales_frame.dispose();
                ShowSalesDetail saleDetails = new ShowSalesDetail(SalesDetails_orderId);
            }
        }catch (Exception err){
            System.out.println("View Sales"+err);
        }

    }


}
