package Vieww.Admin;

import Vieww.Home;

import javax.swing.*;
import java.awt.*;

public class AdminFunctionality_UI extends Home {

    JButton addProduct;

    @Override
    public void mainHeading() {
        JLabel heading = new JLabel("ADMIN PAGE");
        heading.setBounds(480,100,600,100);
        heading.setForeground(Color.ORANGE);
        heading.setFont(new Font("Serif", Font.BOLD, 60));
        home_frame.add(heading);
    }

    @Override
    public void admin_login(Frame home_frame) {

    }

    @Override
    public void workingOf_OrderMedicine(JButton orderMedicine) {
        orderMedicine.addActionListener(el->{
            AdminOrder_Medicine order = new AdminOrder_Medicine();
        });
    }

    @Override
    public void workingOf_ViewMedicines(JButton viewMedicine) {
        viewMedicine.addActionListener(el->{
            Admin_ViewMedicine view = new Admin_ViewMedicine();

        });
    }

    @Override
    public void workingOf_ViewSales(JButton viewSale) {
        viewSale.addActionListener(el->{
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

    public AdminFunctionality_UI(){
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
    }
}
