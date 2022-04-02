package View.Admin;

import Model.Product;
import View.Receipt;

import javax.swing.*;
import java.util.ArrayList;

public class AdminReceipt extends Receipt {

    public AdminReceipt() {

    }

    @Override
    public void workingOfExit_Button(JButton exit) {
        exit.addActionListener(el->{
            receipt_frame.dispose();
            AdminOrder_Medicine order = new AdminOrder_Medicine();
        });
    }
}
