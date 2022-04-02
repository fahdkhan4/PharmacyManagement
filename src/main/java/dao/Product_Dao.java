package dao;

import Model.*;
import Service.FindMedicine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface Product_Dao {


//                                                                    get all the products from DB
     static List<Product> getAllProducts(){
          List<Product> results = new ArrayList<>();
          try{
               ResultSet rs = DBService.query("select * from medicines");
               while (true){
                    assert rs != null;
                    if (!rs.next())
                         break;
                    results.add(new Product
                            (
                                    Long.valueOf(rs.getString("id")),
                                    rs.getString("m_name"),
                                    rs.getString("m_varient"),
                                    Double.valueOf(rs.getString("m_price")),
                                    Integer.valueOf(rs.getString("m_quantity"))));
               }

          } catch (SQLException e) {
               e.printStackTrace();
          }
          return results;
     };


//                                                                    Add product into database
     void insertProduct_ToDB(Product product);

//                                                                    Delete medicine from database
     void delete_Medicines(HashSet<Long> productCode);

//                                                                    Update medicine from database
     void updateMedicine(Product updateProduct);



}
