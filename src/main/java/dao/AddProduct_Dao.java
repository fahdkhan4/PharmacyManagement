package dao;
import Model.Product;



public class AddProduct_Dao {


    public void insertProduct_ToDB(Product product) {

        String query = "INSERT INTO medicines VALUES ("+product.getId()+",'"+product.getMedicine_name()+"','"+product.getMedicine_varient()+"','"+product.getMedicine_price()+"','"+product.getMedicine_quantity()+"')";
        DBService.PreparedQuery(query);

    }


public static void main(String[] args) {


}

}
