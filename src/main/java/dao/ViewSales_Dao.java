package dao;

import Model.ProductCart_Model;
import Model.SaleRecord;
import View.ViewSales;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewSales_Dao implements Sales_Dao {

    @Override
    public void insertingSalesRecord() {
        String query = "INSERT INTO sales (order_id,emp_name,order_date,cost_price,sell_price,profit) SELECT distinct o.id, o.user_name , o.order_date , SUM(p.cost_price * c.product_qty) AS costPrice , SUM(c.price_unit) AS  priceSell , (SUM(c.price_unit) - SUM(p.cost_price * c.product_qty)) AS profit  FROM ((cart c\n" +
                "INNER JOIN productorder o ON o.id = c.order_id)\n" +
                "INNER JOIN products p ON p.barcode = c.product_barcode)\n" +
                " WHERE o.state = \"Completed\" GROUP BY o.id HAVING o.id NOT IN (SELECT order_id FROM sales)";
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        }catch (Exception e){

        }
    }

    @Override
    public List<SaleRecord> gettingSalesRecord_DB() {
        List<SaleRecord> saleRecords = new ArrayList<>();

        try{
            ResultSet rs = DBService.query("SELECT * FROM sales");
            while (true){
                assert rs != null;
                if(!rs.next())
                    break;
                    saleRecords.add(new SaleRecord(
                            Integer.valueOf(rs.getString("id")),
                            Integer.valueOf(rs.getString("order_id")),
                            rs.getString("emp_name"),
                            LocalDate.parse(rs.getString("order_date")),
                            Double.valueOf(rs.getString("cost_price")),
                            Double.valueOf(rs.getString("sell_price")),
                            Double.valueOf(rs.getString("profit"))
                    ));
            }
            DBService.con.close();
        }
        catch (Exception error){
            System.out.println(error);
        }

        return saleRecords;
    }

    @Override
    public List<SaleRecord> filterByName(){
        List<SaleRecord> filterName = new ArrayList<>();

        try{
            ResultSet rs = DBService.query("SELECT * FROM sales WHERE emp_name = '"+ViewSales.emp_name+"'");

            while (true) {
                assert rs != null;
                if (!rs.next())
                    break;
                filterName.add(new SaleRecord(
                        Integer.valueOf(rs.getString("id")),
                        Integer.valueOf(rs.getString("order_id")),
                        rs.getString("emp_name"),
                        LocalDate.parse(rs.getString("order_date")),
                        Double.valueOf(rs.getString("cost_price")),
                        Double.valueOf(rs.getString("sell_price")),
                        Double.valueOf(rs.getString("profit"))
                ));
            }
            DBService.con.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return filterName;
    }

    @Override
    public List<SaleRecord> filterByDate() {
        List<SaleRecord> filterdate = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("SELECT * FROM sales where order_date BETWEEN '"+ViewSales.firstdate+"' AND '"+ViewSales.seconddate+"'");

            while (true) {
                assert rs != null;
                if (!rs.next())
                    break;
                filterdate.add(new SaleRecord(
                        Integer.valueOf(rs.getString("id")),
                        Integer.valueOf(rs.getString("order_id")),
                        rs.getString("emp_name"),
                        LocalDate.parse(rs.getString("order_date")),
                        Double.valueOf(rs.getString("cost_price")),
                        Double.valueOf(rs.getString("sell_price")),
                        Double.valueOf(rs.getString("profit"))
                ));
            }
            DBService.con.close();
        }catch (Exception e){
            System.out.println(e);
        }

        return filterdate;
    }

    @Override
    public List<SaleRecord> sortByProfit() {
        List<SaleRecord> sort = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("SELECT * FROM sales ORDER BY profit DESC");
            while (true) {
                assert rs != null;
                if (!rs.next())
                    break;
                sort.add(new SaleRecord(
                        Integer.valueOf(rs.getString("id")),
                        Integer.valueOf(rs.getString("order_id")),
                        rs.getString("emp_name"),
                        LocalDate.parse(rs.getString("order_date")),
                        Double.valueOf(rs.getString("cost_price")),
                        Double.valueOf(rs.getString("sell_price")),
                        Double.valueOf(rs.getString("profit"))
                ));
            }
            DBService.con.close();
        }catch (Exception e){
            System.out.println(e);
        }
        return sort;
    }

    @Override
    public List<ProductCart_Model> gettingSalesDetails() {
        List<ProductCart_Model> model = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("SELECT * FROM cart WHERE order_id = "+ViewSales.SalesDetails_orderId);
            while (true){
                assert rs!= null;
                if(!rs.next())
                    break;
                model.add(new ProductCart_Model(
                        Long.parseLong(rs.getString("product_barcode")),
                        rs.getString("product_name"),
                        rs.getString("product_varient"),
                        Double.valueOf(rs.getString("product_price")),
                        Double.valueOf(rs.getString("price_unit")),
                        Integer.valueOf(rs.getString("product_qty")),
                        Integer.valueOf(rs.getString("order_id"))
                ));

            }
            DBService.con.close();
        }catch (Exception error){
            System.out.println(error);
        }

        return model;
    }


}
