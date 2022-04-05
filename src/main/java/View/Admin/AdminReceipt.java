package View.Admin;

import View.Receipt;

import javax.swing.*;

public class AdminReceipt extends Receipt {

    public AdminReceipt() {

    }

    @Override
    public void workingOfExitButton(JButton exit) {
        dispose();
        AdminOrder_Medicine medicine = new AdminOrder_Medicine();
    }
}
