package Vieww.Admin;

import Vieww.ViewSales;

import javax.swing.*;

public class AdminViewSales extends ViewSales {

    @Override
    public void working_ofExitButton(JButton exit) {
        exit.addActionListener(el->{
            AdminFunctionality_UI admin = new AdminFunctionality_UI();
        });
    }


}
