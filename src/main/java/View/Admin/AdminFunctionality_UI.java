package View.Admin;

import View.Employee_Functionality;

import javax.swing.*;
import java.awt.*;

public class AdminFunctionality_UI extends Employee_Functionality {

    JButton addProduct;
    JLabel accountHandler = new JLabel();


    public AdminFunctionality_UI() {
//                                                                      adding add product button
        home_frame.setTitle("Admin Home Page");
        addProduct = new JButton("Add Product");
        addProduct.setBounds(500,300,300,40);
        addProduct.setForeground(Color.BLACK);
        addProduct.setBackground(Color.ORANGE);
        home_frame.add(addProduct);

        addProduct.addActionListener(el->{
            home_frame.dispose();
            AddProduct product = new AddProduct();
        });

//                                                                              adding the name of user
        activeEmployeeName(accountHandler);
    }

    @Override
    public void mainHeading() {
        JLabel heading = new JLabel("ADMIN PAGE");
        heading.setBounds(480,100,600,100);
        heading.setForeground(Color.ORANGE);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        home_frame.add(heading);
    }
    @Override
    public void activeEmployeeName(JLabel accountHandler) {

        accountHandler.setText("Account handler : "+Admin.admin_name);
        accountHandler.setForeground(Color.ORANGE);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 25));
        accountHandler.setBounds(30,30,300,100);
        home_frame.add(accountHandler);
    }


    @Override
    public void workingOf_OrderMedicine(JButton orderMedicine) {
        orderMedicine.addActionListener(el->{
            home_frame.dispose();
            AdminOrder_Medicine order = new AdminOrder_Medicine();
        });
    }

    @Override
    public void workingOf_ViewMedicines(JButton viewMedicine) {
        viewMedicine.addActionListener(el->{
            home_frame.dispose();
            Admin_ViewMedicine view = new Admin_ViewMedicine();

        });
    }

    @Override
    public void workingOf_ViewSales(JButton viewSale) {
        viewSale.addActionListener(el->{
            home_frame.dispose();
            AdminViewSales admin = new AdminViewSales();
        });
    }

    @Override
    public void workingOf_Exit(JButton exit) {
        exit.addActionListener(el->{
            home_frame.dispose();
            Admin admin = new Admin();
        });
    }


}
