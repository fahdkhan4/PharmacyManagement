package dao;

import Model.OrderProduct_Model;
import Model.Product;
import Model.ProductCart_Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderProduct_Dao {

    //                                                                     adding order information in database

    static List<Product> getCart_Product(){
        List<Product> cartProduct = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("select * from cart where order_id="+DBService.orderID);
            while (true){
                assert rs != null;
                if (!rs.next())
                    break;
                cartProduct.add(new Product
                        (
                                Long.valueOf(rs.getString("product_code")),
                                rs.getString("product_name"),
                                rs.getString("product_varient"),
                                Double.valueOf(rs.getString("price_unit")),
                                Integer.valueOf(rs.getString("product_qty"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartProduct;

    }

    void inserting_OrderInformation(OrderProduct_Model productmodel);

    void inserting_cartProduct(ProductCart_Model cart_model);

    void update_OrderInformation(OrderProduct_Model updateproduct);

    void delete_OrderInformation();

    void delete_cartProduct();

}
