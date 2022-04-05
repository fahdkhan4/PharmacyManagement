package View.Admin;

import Model.Invoice;
import Model.OrderProduct_Model;
import View.EmployeeLogin;

import View.OrderMedicine;
import View.Receipt;
import dao.DBService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;


public class AdminOrder_Medicine extends OrderMedicine {

    @Override
    public void workingOf_BuyButton(JButton buy_product) {
        buy_product.addActionListener(el->{
            this.userorder_table = null;
            order_frame.dispose();
            OrderProduct_Model orderProduct_model = new OrderProduct_Model(EmployeeLogin.activeEmployee, LocalDate.now(), "Completed");
            orderProduct_functionality.update_OrderInformation(orderProduct_model);

//                  sending invoice data
            Invoice invoice = new Invoice(DBService.orderID,AdminFunctionality_UI.admin_name, LocalDate.now());
            invoice_dao.insertInto_InvoiceDB(invoice);
//                 getting product and invoice data through joins
            invoice_dao.getDataOf_InvoiceLine();
//                 inserting the data into invoice line to show it to the recept
            invoice_dao.insertingInvoiceDataIn_InvoiceLine();

//
            Receipt example = new Receipt();
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
                   cartProduct.delete_cartProduct();
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
