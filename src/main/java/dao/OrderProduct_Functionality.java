package dao;

import Model.Invoice;
import Model.OrderProduct_Model;
import Model.ProductCart_Model;

import java.sql.ResultSet;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class OrderProduct_Functionality implements OrderProduct_Dao {


//    @Override
//    public List<OrderProduct_Model> getOrderProduct() {
//        List<OrderProduct_Model> getorder = new ArrayList<>();
//
//        ZoneId defaultzone = ZoneId.systemDefault();
//        Instant instant  =
//
//        try {
//            ResultSet rs = DBService.query("SELECT * from productorder where id = "+DBService.orderID);
//
//            while (true){
//                assert rs != null;
//                if (!rs.next())
//                    break;
//                getorder.add(new OrderProduct_Model(
//                        rs.getString("user_name"),
//                        LocalDate.
//                        rs.getString("state")
//
//                ))
//
//
//            }
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return getorder;
//    }

    @Override
    public void inserting_OrderInformation(OrderProduct_Model productmodel) {
        System.out.println(productmodel);
        String query = "INSERT INTO productorder(user_name,order_date,state) VALUES ('"+productmodel.getEmployee_name()+"','"+productmodel.getOrder_date()+"','"+productmodel.getState()+"')";
        DBService.PreparedQuery_GettingKey(query);
    }


    @Override
    public void update_OrderInformation(OrderProduct_Model updateproduct) {
        String query = "UPDATE productorder SET state = '"+updateproduct.getState()+"' WHERE id = "+DBService.orderID;
        DBService.PreparedQuery(query);
    }

    @Override
    public void delete_OrderInformation() {
        String query = "DELETE FROM productorder WHERE id ="+DBService.orderID;
        DBService.PreparedQuery(query);
    }



}
