package dao;

import Model.Product;
import Model.ProductCart_Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserCartProduct_Dao {

    //                                                                     adding order information in database

    static List<ProductCart_Model> getCart_Product(){
        List<ProductCart_Model> cartProduct = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("select * from cart where order_id="+DBService.orderID);
            while (true){
                assert rs != null;
                if (!rs.next())
                    break;
                cartProduct.add(new ProductCart_Model
                        (
                                Long.valueOf(rs.getString("product_barcode")),
                                rs.getString("product_name"),
                                rs.getString("product_varient"),
                                Double.valueOf(rs.getString("product_price")),
                                Double.valueOf(rs.getString("price_unit")),
                                Integer.valueOf(rs.getString("product_qty")),
                                Integer.valueOf(rs.getString("order_id"))
                        ));
            }
            DBService.con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartProduct;

    }
//                                                                          insert cart product
    void inserting_cartProduct(ProductCart_Model cart_model);

//                                                                          delete cart product
    void delete_cartProduct();


    void updateCartProductQuantity(ProductCart_Model updateqty);

    void updateQuantityOFProductOnCancelation();

    Double cartProductTotalAmount();

    void insertIntoCartBy_Barcode();

    void updateCartProductQTY(Long barcode,Integer remaingqty,Double productprice);

    void removeAllproductQTY_0();
}
