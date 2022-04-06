package dao;

import Model.Product;
import Model.ProductCart_Model;

import java.sql.ResultSet;

public class CartProduct implements  UserCartProduct_Dao{


    @Override
    public void inserting_cartProduct(ProductCart_Model cart_model) {
        String query = "INSERT INTO cart(product_barcode,product_name,product_varient,product_price,price_unit,product_qty,order_id) VALUES ("+cart_model.getProduct_code()+",'"+cart_model.getProduct_name()+"','"+cart_model.getProduct_varient()+"',"+cart_model.getProduct_price()+","+cart_model.getPrice_unit()+","+cart_model.getProduct_quantity()+","+cart_model.getOrder_id()+")";
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

    @Override
    public void updateCartProductQuantity(ProductCart_Model updateqty) {
        String query = "UPDATE cart SET product_qty = "+updateqty.getProduct_quantity()+",price_unit = "+updateqty.getPrice_unit()+" WHERE product_barcode = "+updateqty.getProduct_code();
        DBService.PreparedQuery(query);
    }

    @Override
    public void updateQuantityOFProductOnCancelation() {

        for (ProductCart_Model cart:UserCartProduct_Dao.getCart_Product()) {
            Integer finalquantity = 0;
            for (Product product:Product_Dao.getAllProducts()) {
                if(cart.getProduct_code().equals(product.getBarCode())){
                    finalquantity = cart.getProduct_quantity() + product.getMedicine_quantity();
                    String query = "UPDATE products SET product_qty = "+finalquantity+" WHERE barcode = "+cart.getProduct_code();
                    DBService.PreparedQuery(query);
                }
            }
        }
    }

    @Override
    public  Double cartProductTotalAmount() {
        Double cartTotalAmount = 0.0;
        try{
            ResultSet rs = DBService.query("SELECT SUM(price_unit) FROM cart WHERE order_id ="+DBService.orderID);
            while(true){
                assert rs != null;
                if (!rs.next())
                    break;
                    cartTotalAmount = Double.valueOf(rs.getString("SUM(price_unit)"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return cartTotalAmount;
    }

}
