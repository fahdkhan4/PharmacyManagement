package com.pharmacymanagement.View.Admin;

import com.pharmacymanagement.View.ShowSalesDetail;

import javax.swing.*;


public class Admin_ShowSalesDetails extends ShowSalesDetail {


    public Admin_ShowSalesDetails(Integer order_id) {
        super(order_id);
    }

    public void workingOfExitButton(JButton exit) {
        exit.addActionListener(el->{
            showOrder_frame.dispose();
            AdminViewSales sales = new AdminViewSales();
        });
    }
}
