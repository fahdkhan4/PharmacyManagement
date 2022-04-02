package dao;

import Model.OrderProduct_Model;
import Model.ProductCart_Model;

public class OrderProduct_Functionality implements OrderProduct_Dao {

    @Override
    public void inserting_OrderInformation(OrderProduct_Model productmodel) {
        System.out.println(productmodel);
        String query = "INSERT INTO productorder(user_name,order_date,state) VALUES ('"+productmodel.getEmployee_name()+"','"+productmodel.getOrder_date()+"','"+productmodel.getState()+"')";
        DBService.PreparedQuery_GettingKey(query);
    }

    @Override
    public void inserting_cartProduct(ProductCart_Model cart_model) {
        String query = "INSERT INTO cart(product_code,product_name,product_varient,price_unit,product_qty,order_id) VALUES ("+cart_model.getProduct_code()+",'"+cart_model.getProduct_name()+"','"+cart_model.getProduct_varient()+"',"+cart_model.getPrice_unit()+","+cart_model.getProduct_quantity()+","+cart_model.getOrder_id()+")";
        DBService.PreparedQuery(query);
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

    @Override
    public void delete_cartProduct() {
        String query = "DELETE FROM cart WHERE order_id ="+DBService.orderID;
        DBService.PreparedQuery(query);
    }


}
