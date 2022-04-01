package dao;

import Model.OrdeProduct_Model;
import Model.Product;
import Model.ProductCart_Model;

import java.util.HashSet;

public class ProductFunctionality_Dao implements Product_Dao {


    @Override
    public void insertProduct_ToDB(Product product) {
        String query = "INSERT INTO medicines VALUES ("+product.getId()+",'"+product.getMedicine_name()+"','"+product.getMedicine_varient()+"','"+product.getMedicine_price()+"','"+product.getMedicine_quantity()+"')";
        DBService.PreparedQuery(query);
    }

    @Override
    public void delete_Medicines(HashSet<Long> productCode) {
        String query ;
        for (Long code:productCode) {
            query = "DELETE FROM medicines  WHERE id="+code;
            DBService.PreparedQuery(query);
        }
    }

    public void updateMedicine(Product updateProduct){

        String updateProduct_query = "UPDATE medicines SET m_name = '"+updateProduct.getMedicine_name()+"'," +
                "m_varient = '"+updateProduct.getMedicine_varient()+"', m_price = '"+updateProduct.getMedicine_price()+"'," +
                "m_quantity = '"+updateProduct.getMedicine_quantity()+"' where id = "+updateProduct.getId();
        DBService.PreparedQuery(updateProduct_query);
    }

    @Override
    public void inserting_OrderInformation(OrdeProduct_Model productmodel) {
        System.out.println(productmodel);
        String query = "INSERT INTO productorder(user_name,order_date,state) VALUES ('"+productmodel.getEmployee_name()+"','"+productmodel.getOrder_date()+"','"+productmodel.getState()+"')";
        DBService.PreparedQuery_GettingKey(query);
    }

    @Override
    public void inserting_cartProduct(ProductCart_Model cart_model) {
        String query = "INSERT INTO cart(product_code,product_name,product_varient,price_unit,product_qty,order_id) VALUES ("+cart_model.getProduct_code()+",'"+cart_model.getProduct_name()+"','"+cart_model.getProduct_varient()+"',"+cart_model.getPrice_unit()+","+cart_model.getProduct_quantity()+","+cart_model.getOrder_id()+")";
        DBService.PreparedQuery(query);
    }

    public static void main(String[] args) {

    }
}
