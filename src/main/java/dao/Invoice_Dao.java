package dao;

import Model.Invoice;
import Model.Invoice_line;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Invoice_Dao implements InvoiceDB_Dao {


    @Override
    public void insertInto_InvoiceDB(Invoice invoice) {

        String query = "INSERT INTO invoice(order_id,emp_name,invoice_date) VALUES ("+invoice.getOrder_id()+",'"+invoice.getEmp_name()+"','"+invoice.getDate()+"')";
        DBService.PreparedQuery(query);

    }

    @Override
    public List<Invoice_line> getDataOf_InvoiceLine() {
        List<Invoice_line> invoice_data = new ArrayList<>();

        try{
              ResultSet rs =DBService.query("SELECT e.id , e.order_id ,c.product_name ,c.product_varient , c.price_unit, product_qty FROM cart c\n" +
                    "INNER JOIN invoice e ON e.order_id = c.order_id where e.order_id= "+DBService.orderID);

              while(true){
                  assert rs != null;
                  if (!rs.next())
                      break;
                    invoice_data.add(new Invoice_line(
                            Integer.valueOf(rs.getString("id")),
                            Integer.valueOf(rs.getString("order_id")),
                            rs.getString("product_name"),
                            Double.valueOf(rs.getString("price_unit")),
                            Integer.valueOf(rs.getString("product_qty")),
                            rs.getString("product_varient")
                    ));
              }


        }catch (Exception e){
            System.out.println(e);
        }

        return invoice_data;
    }

    @Override
    public void insertingInvoiceDataIn_InvoiceLine() {
        for (Invoice_line invoice_Data:getDataOf_InvoiceLine()) {
            String query  = "INSERT INTO invoice_line VALUES ("+invoice_Data.getInvoice_id()+","+invoice_Data.getOrder_id()+",'"+invoice_Data.getItem_name()+"','"+invoice_Data.getItem_varient()+"',"+invoice_Data.getItem_price()+","+invoice_Data.getItem_qty()+")";
            DBService.PreparedQuery(query);
        }
    }

    @Override
    public List<Invoice_line> gettingInvoiceLine_Data(){
        List<Invoice_line> invoice_lines = new ArrayList<>();

        try {
            ResultSet rs = DBService.query("SELECT * FROM invoice_line  WHERE order_id = " + DBService.orderID);
            while(true){
                assert rs != null;
                if (!rs.next())
                    break;
                invoice_lines.add(new Invoice_line(
                        Integer.valueOf(rs.getString("invoice_id")),
                        Integer.valueOf(rs.getString("order_id")),
                        rs.getString("item_name"),
                        Double.valueOf(rs.getString("item_price")),
                        Integer.valueOf(rs.getString("item_qty")),
                        rs.getString("item_varient")
                ));

            }
        }catch (Exception e){
            System.out.println(e);
        }
        System.out.println(invoice_lines);
        return invoice_lines;
    }
}


