package Vieww.Admin;

import Vieww.OrderMedicine;

import javax.swing.*;

public class AdminOrder_Medicine extends OrderMedicine {

    @Override
    public void working_ofExitButton(JButton exit) {
       exit.addActionListener(el->{
           AdminFunctionality_UI admin = new AdminFunctionality_UI();
       });
    }


}
