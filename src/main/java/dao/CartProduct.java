package dao;

import Model.Product;
import Model.ProductCart_Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CartProduct implements  UserCartProduct_Dao{

    ProductFunctionality_Dao product_dao = new ProductFunctionality_Dao();

    @Override
    public void inserting_cartProduct(ProductCart_Model cart_model) {
        String query = "INSERT INTO cart(product_barcode,product_name,product_varient,product_price,price_unit,product_qty,order_id) VALUES ("+cart_model.getProduct_code()+",'"+cart_model.getProduct_name()+"','"+cart_model.getProduct_varient()+"',"+cart_model.getProduct_price()+","+cart_model.getPrice_unit()+","+cart_model.getProduct_quantity()+","+cart_model.getOrder_id()+")";
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void delete_cartProduct() {
        String query = "DELETE FROM cart WHERE order_id ="+DBService.orderID;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCartProductQuantity(ProductCart_Model updateqty) {
        String query = "UPDATE cart SET product_qty = "+updateqty.getProduct_quantity()+",price_unit = "+updateqty.getPrice_unit()+" WHERE product_barcode = "+updateqty.getProduct_code();
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateQuantityOFProductOnCancelation() {

        for (ProductCart_Model cart:UserCartProduct_Dao.getCart_Product()) {
            Integer finalquantity = 0;
            for (Product product:Product_Dao.getAllProductsZerosALSO()) {
                if(cart.getProduct_code().equals(product.getBarCode())){
                    finalquantity = cart.getProduct_quantity() + product.getMedicine_quantity();
                    String query = "UPDATE products SET product_qty = "+finalquantity+" WHERE barcode = "+cart.getProduct_code();
                    try {
                        DBService.PreparedQuery(query);
                        DBService.con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
            DBService.con.close();
        }catch (Exception e){
            System.out.println("Total amount went 0"+e);
        }
        return cartTotalAmount;
    }

    @Override
    public void insertIntoCartBy_Barcode() {

        String query = "INSERT INTO cart(product_barcode,product_name,product_varient,product_price,price_unit,product_qty,order_id) " +
                " VALUES ("+product_dao.searchBybarcode().get(0).getBarCode()+",'"+product_dao.searchBybarcode().get(0).getMedicine_name()+"','"+product_dao.searchBybarcode().get(0).getMedicine_varient()+"',"+product_dao.searchBybarcode().get(0).getMedicine_Saleprice()+","+product_dao.searchBybarcode().get(0).getMedicine_Saleprice()+",1,"+DBService.orderID+") ";
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

//                                          Related to barcode scanner procedure


    @Override
    public void updateCartProductQTY(Long barcode, Integer remaingqty,Double productPrice) {
        String query = "UPDATE cart SET product_qty = "+remaingqty+" , price_unit = "+productPrice+"  WHERE product_barcode = "+barcode+ " AND  order_id = "+DBService.orderID;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAllproductQTY_0() {
        String query = "DELETE FROM cart WHERE product_qty = 0 AND order_id = "+DBService.orderID;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//                                                          update cart product qty

}
