package View.Admin;

import View.Receipt;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AdminReceipt extends Receipt {
    public AdminReceipt() {
        super();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    @Override
    public void workingOfExitButton(JButton exit) {
        dispose();
        AdminOrder_Medicine medicine = new AdminOrder_Medicine();
    }
}
