package View.Admin;

import View.OrderMedicine;

import javax.swing.*;

public class AdminOrder_Medicine extends OrderMedicine {

    @Override
    public void workingOf_BuyButton(JButton buy_product) {
        buy_product.addActionListener(el->{
            order_frame.dispose();
            AdminReceipt admin = new AdminReceipt(userProducts);
        });
    }

    @Override
    public void working_ofExitButton(JButton exit) {
       exit.addActionListener(el->{
           order_frame.dispose();
           AdminFunctionality_UI admin = new AdminFunctionality_UI();
       });
    }


}
