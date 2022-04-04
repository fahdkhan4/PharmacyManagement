package dao;

import Model.ProductCart_Model;

public class CartProduct implements  UserCartProduct_Dao{

    @Override
    public void inserting_cartProduct(ProductCart_Model cart_model) {
        String query = "INSERT INTO cart(product_barcode,product_name,product_varient,price_unit,product_qty,order_id) VALUES ("+cart_model.getProduct_code()+",'"+cart_model.getProduct_name()+"','"+cart_model.getProduct_varient()+"',"+cart_model.getPrice_unit()+","+cart_model.getProduct_quantity()+","+cart_model.getOrder_id()+")";
        DBService.PreparedQuery(query);
    }

    @Override
    public void delete_cartProduct() {
        String query = "DELETE FROM cart WHERE order_id ="+DBService.orderID;
        DBService.PreparedQuery(query);
    }

    @Override
    public void removeSpecific_CartProduct(Long barcode) {
        String query = "DELETE FROM cart WHERE product_barcode = "+barcode;
        DBService.PreparedQuery(query);
    }


}
