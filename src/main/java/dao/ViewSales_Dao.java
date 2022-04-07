package dao;

import Model.SaleRecord;
import View.ViewSales;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ViewSales_Dao implements Sales_Dao {

    @Override
    public void insertingSalesRecord() {
        String query = "INSERT INTO sales (order_id,emp_name,order_date,cost_price,sell_price,profit) SELECT distinct o.id, o.user_name , o.order_date , SUM(c.price_unit)AS  priceSell , SUM(p.cost_price * c.product_qty)  costPrice  , (SUM(c.price_unit) - SUM(p.cost_price * c.product_qty)) AS profit  FROM ((cart c\n" +
                "INNER JOIN productorder o ON o.id = c.order_id)\n" +
                "INNER JOIN products p ON p.barcode = c.product_barcode)\n" +
                " WHERE o.state = \"Completed\" GROUP BY o.id HAVING o.id NOT IN (SELECT order_id FROM sales)";

        DBService.PreparedQuery(query);
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
        }catch (Exception e){
            System.out.println(e);
        }
        return filterdate;
    }


}
