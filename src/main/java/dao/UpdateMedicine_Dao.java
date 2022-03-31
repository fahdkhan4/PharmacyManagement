package dao;

import Model.Product;

public class UpdateMedicine_Dao {


    public void updateMedicine(Product updateProduct){

        String updateProduct_query = "UPDATE medicines SET m_name = '"+updateProduct.getMedicine_name()+"'," +
                "m_varient = '"+updateProduct.getMedicine_varient()+"', m_price = '"+updateProduct.getMedicine_price()+"'," +
                "m_quantity = '"+updateProduct.getMedicine_quantity()+"' where id = "+updateProduct.getId();
        DBService.PreparedQuery(updateProduct_query);
    }
}
