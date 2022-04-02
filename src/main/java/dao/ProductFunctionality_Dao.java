package dao;

import Model.Product;

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


    public static void main(String[] args) {

    }
}
