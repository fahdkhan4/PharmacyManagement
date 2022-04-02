package View.Admin;

import Model.OrderProduct_Model;
import View.EmployeeLogin;

import View.OrderMedicine;
import View.Receipt;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class AdminOrder_Medicine extends OrderMedicine {

    @Override
    public void workingOf_BuyButton(JButton buy_product) {
        buy_product.addActionListener(el->{
            order_frame.dispose();
            OrderProduct_Model orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(),"Completed");
            orderProduct_functionality.update_OrderInformation(orderProduct_model);
            Receipt receipt = new Receipt();
        });
    }

    @Override
    public void activeEmployeeName(JLabel accountHandler) {
        accountHandler.setText("Account handler : "+AdminFunctionality_UI.admin_name);
        accountHandler.setForeground(Color.BLACK);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 20));
        accountHandler.setBounds(1000,50,300,50);
        order_frame.add(accountHandler);
    }

    @Override
    public void working_ofExitButton(JButton exit) {
       exit.addActionListener(el->{

           if(this.userorder_table != null) {
               int res = JOptionPane.showOptionDialog(order_frame, "Are you Sure \n Exit will remove the cart ", "Order", JOptionPane.DEFAULT_OPTION,
                       JOptionPane.INFORMATION_MESSAGE, null, null, null);
               if(res == 0){
                   order_frame.dispose();
                   orderProduct_functionality.delete_cartProduct();
                   orderProduct_functionality.delete_OrderInformation();
                   AdminFunctionality_UI admin = new AdminFunctionality_UI();
               }
           }
           else{
               order_frame.dispose();
               AdminFunctionality_UI admin = new AdminFunctionality_UI();
           }
       });
    }

}
