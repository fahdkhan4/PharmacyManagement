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

            if(this.userorder_table.getRowCount() > 0) {
                this.userorder_table = null;
                order_frame.dispose();
                System.out.println(Admin.admin_name);
                OrderProduct_Model orderProduct_model = new OrderProduct_Model(Admin.admin_name, LocalDate.now(), "Completed");
                orderProduct_functionality.update_OrderInformation(orderProduct_model);

//                  sending invoice data
                Invoice invoice = new Invoice(DBService.orderID, Admin.admin_name, LocalDate.now());
                invoice_dao.insertInto_InvoiceDB(invoice);
//                 getting product and invoice data through joins
                invoice_dao.getDataOf_InvoiceLine();
//                 inserting the data into invoice line to show it to the recept
                invoice_dao.insertingInvoiceDataIn_InvoiceLine();
//                  inserting all the sales
                sales_dao.insertingSalesRecord();

                AdminReceipt receipt = new AdminReceipt();
            }
            else{
                JOptionPane.showMessageDialog(order_frame,"Cart is empty");
            }
        });
    }

    @Override
    public void activeEmployeeName(JLabel accountHandler) {
        System.out.println(Admin.admin_name);
        accountHandler.setText("Account handler : "+Admin.admin_name);
        accountHandler.setForeground(Color.BLACK);
        accountHandler.setFont(new Font("Serif", Font.BOLD, 20));
        accountHandler.setBounds(1000,50,300,50);
        order_frame.add(accountHandler);
    }

    @Override
    public void working_ofExitButton(JButton exit) {
       exit.addActionListener(el->{

           if(this.userorder_table.getRowCount() > 0) {
               int res = JOptionPane.showOptionDialog(order_frame, "Are you Sure \n Exit will remove the cart ", "Order", JOptionPane.DEFAULT_OPTION,
                       JOptionPane.INFORMATION_MESSAGE, null, null, null);
               if(res == 0){
                   order_frame.dispose();
                   cartProduct.updateQuantityOFProductOnCancelation();
                   cartProduct.delete_cartProduct();
                   orderProduct_functionality.delete_OrderInformation();
                   this.userorder_table = null;
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
