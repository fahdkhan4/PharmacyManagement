package View.Admin;

import View.ViewSales;

import javax.swing.*;

public class AdminViewSales extends ViewSales {

    @Override
    public void working_ofExitButton(JButton exit) {
        exit.addActionListener(el->{
            viewSales_frame.dispose();
            AdminFunctionality_UI admin = new AdminFunctionality_UI();
        });
    }


}
