package View.Admin;

import View.ShowSalesDetail;
import View.ViewSales;

import javax.swing.*;
import java.awt.event.MouseEvent;

public class AdminViewSales extends ViewSales {

    @Override
    public void working_ofExitButton(JButton exit) {
        exit.addActionListener(el->{
            viewSales_frame.dispose();
            AdminFunctionality_UI admin = new AdminFunctionality_UI();
        });
    }

    @Override
    public void workingOnMouse(MouseEvent e) {

        try {
            Integer sale_row, sale_column;

            sale_row = viewSales_table.rowAtPoint(e.getPoint());
            sale_column = 1;
            if(sale_row >= 0) {
                SalesDetails_orderId = (Integer) viewSales_table.getModel().getValueAt(sale_row, sale_column);
                viewSales_frame.dispose();
                Admin_ShowSalesDetails saledetails = new Admin_ShowSalesDetails(SalesDetails_orderId);
            }
        }catch (Exception error){
            System.out.println("Admin view Sales"+error);
        }


    }
}
