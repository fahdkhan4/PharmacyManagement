package dao;
import products.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class ProductDao {
    public static List<Product> getAllProducts() {
        List<Product> results = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("select * from medicines");
            while (true){
                assert rs != null;
                if (!rs.next())
                    break;
                results.add(new Product
                        (
                                Integer.valueOf(rs.getString("id")),
                                rs.getString("m_name"),
                                rs.getString("m_varient"),
                                Double.valueOf(rs.getString("m_price")),
                                Integer.valueOf(rs.getString("m_quantity"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(getAllProducts());
    }


}
