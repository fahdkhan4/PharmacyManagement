package dao;

import Model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public interface Product_Dao {

     //                                                                    get all the products from DB
     static List<Product> getAllProducts() {
          List<Product> results = new ArrayList<>();
          try {
               ResultSet rs = DBService.query("select * from products where product_qty <> 0");
               while (true) {
                    assert rs != null;
                    if (!rs.next())
                         break;
                    results.add(new Product
                            (
                                    Long.valueOf(rs.getString("barcode")),
                                    rs.getString("product_name"),
                                    rs.getString("product_varient"),
                                    Double.valueOf(rs.getString("cost_price")),
                                    Double.valueOf(rs.getString("sell_price")),
                                    Integer.valueOf(rs.getString("product_qty"))));
               }
               DBService.con.close();
          } catch (SQLException e) {
               e.printStackTrace();
          }
          return results;
     }

     static List<Product> getAllProductsZerosALSO() {
          List<Product> results = new ArrayList<>();
          try {
               ResultSet rs = DBService.query("select * from products");
               while (true) {
                    assert rs != null;
                    if (!rs.next())
                         break;
                    results.add(new Product
                            (
                                    Long.valueOf(rs.getString("barcode")),
                                    rs.getString("product_name"),
                                    rs.getString("product_varient"),
                                    Double.valueOf(rs.getString("cost_price")),
                                    Double.valueOf(rs.getString("sell_price")),
                                    Integer.valueOf(rs.getString("product_qty"))));
               }
               DBService.con.close();

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

//                                                                         update medicine quantity
     void updateMedicine_Quantity(Product updateProductQTY);

//                                                                         searching medicine by barcode
     List<Product> searchBybarcode();
//                                                                         subtract 1 productqty from product
     void updateMedicineQuantity_Barcode(Long barcode,Integer productQTY);

//                                                                         get product quantity of barcode scanner product
     int getProductQuantityOf_barcodeScanner(Long barcode,Integer quantity);
//                                                                         adding quantity in product db after cart remove it
     void updateMedicineQuantity_AfterRemovingFromCart(Long barcode,Integer quantity);

}

