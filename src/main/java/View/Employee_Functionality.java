package View;

import javax.swing.*;
import java.awt.*;


public class Employee_Functionality {

    public JFrame home_frame;
    JButton orderMedicine , viewSale,viewMedicine,exit,X;

    public Employee_Functionality(){

        home_frame = new JFrame("Employee functionality");
        JLabel accountHandler = new JLabel();
        X = new JButton("X");
        orderMedicine = new JButton("Order Medicine");
        viewMedicine = new JButton("Medicine Listing");
        viewSale = new JButton("View Sale");
        exit = new JButton("Log Out");

//                      Main Heading
        mainHeading();

//                                                                              adding the name of user
        activeEmployeeName(accountHandler);
//
        X.setBounds(1300,10,50,50);
        orderMedicine.setBounds(500, 350, 300, 40);
        viewMedicine.setBounds(500, 400, 300, 40);
        viewSale.setBounds(500, 450, 300, 40);
        exit.setBounds(500, 500, 300, 40);


        
        home_frame.getContentPane().setBackground(Color.BLUE);
        home_frame.add(X);
        home_frame.add(orderMedicine);
        home_frame.add(viewMedicine);
        home_frame.add(viewSale);
        home_frame.add(exit);


        home_frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        home_frame.setUndecorated(true);
        home_frame.setLayout(null);
        home_frame.setVisible(true);


        JMenuBar menu_bar;
        JMenu file, edit, about,x;
        JMenuItem cut, copy, paste, selectAll;
        home_frame.getJMenuBar();

//                                                              declaration Menu bar
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");


        file = new JMenu("File");
        edit = new JMenu("Edit");
        about = new JMenu("About");
//
        edit.add(cut);edit.add(copy);edit.add(paste);edit.add(selectAll);
        menu_bar = new JMenuBar();
        menu_bar.add(file);menu_bar.add(edit);menu_bar.add(about);

        home_frame.add(menu_bar);
        home_frame.setJMenuBar(menu_bar);

//                                                                  buttons fonts and color

        X.setForeground(Color.BLACK);
        X.setBackground(Color.ORANGE);
        orderMedicine.setForeground(Color.BLACK);
        orderMedicine.setBackground(Color.ORANGE);
        viewMedicine.setBackground(Color.ORANGE);
        viewMedicine.setForeground(Color.BLACK);
        viewSale.setBackground(Color.ORANGE);
        viewSale.setForeground(Color.BLACK);
        exit.setBackground(Color.ORANGE);
        exit.setForeground(Color.BLACK);

//                                                                      OnClick Working.....
        workingOf_OrderMedicine(orderMedicine);
        workingOf_ViewMedicines(viewMedicine);
        workingOf_ViewSales(viewSale);
        workingOf_Exit(exit);
        workingOf_Exit(X);
    }


    public void mainHeading(){
        JLabel heading = new JLabel("Employee Working Panel");
        heading.setForeground(Color.ORANGE);
        heading.setBounds(330,100,1000,100);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        home_frame.add(heading);
    }


    public void activeEmployeeName(JLabel accountHandler){
        accountHandler.setText("Account handler : "+EmployeeLogin.activeEmployee);
        accountHandler.setForeground(Color.ORANGE);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 25));
        accountHandler.setBounds(30,30,300,100);
        home_frame.add(accountHandler);
    }

    public void workingOf_OrderMedicine(JButton orderMedicine){
        orderMedicine.addActionListener(el->{
            home_frame.dispose();
           OrderMedicine order = new OrderMedicine();
        });

    }
    public void workingOf_ViewMedicines(JButton viewMedicine){
        viewMedicine.addActionListener(el->{
            home_frame.dispose();
            View_Medicines view = new View_Medicines();
        });
    }

    public void workingOf_ViewSales(JButton viewSale){
        viewSale.addActionListener(el->{
            home_frame.dispose();
            ViewSales sale = new ViewSales();
        });
    }

    public void workingOf_Exit(JButton exit){
        exit.addActionListener(el-> {
            home_frame.dispose();
            EmployeeLogin login = new EmployeeLogin();
        });
    }




}
