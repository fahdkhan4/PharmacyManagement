package dao;

import Model.Product;
import View.OrderMedicine;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProductFunctionality_Dao implements Product_Dao {


    @Override
    public void insertProduct_ToDB(Product product) {
        String query = "INSERT INTO products VALUES ("+product.getBarCode()+",'"+product.getMedicine_name()+"','"+product.getMedicine_varient()+"',"+product.getMedicine_Costprice()+","+product.getMedicine_Saleprice()+","+product.getMedicine_quantity()+")";
        DBService.PreparedQuery(query);
    }

    @Override
    public void delete_Medicines(HashSet<Long> productCode) {
        String query ;
        for (Long code:productCode) {
            query = "DELETE FROM products  WHERE barcode="+code;
            DBService.PreparedQuery(query);
        }
    }

    public void updateMedicine(Product updateProduct){

        String updateProduct_query = "UPDATE products SET product_name = '"+updateProduct.getMedicine_name()+"'," +
                "product_varient = '"+updateProduct.getMedicine_varient()+"', sell_price= '"+updateProduct.getMedicine_Saleprice()+"'," +
                "product_qty = '"+updateProduct.getMedicine_quantity()+"' where barcode = "+updateProduct.getBarCode();
        DBService.PreparedQuery(updateProduct_query);
    }

    @Override
    public void updateMedicine_Quantity(Product updateProductQTY) {
        String updateMedicineQTY = "UPDATE products SET product_qty = "+updateProductQTY.getMedicine_quantity()+" WHERE barcode ="+updateProductQTY.getBarCode();
        DBService.PreparedQuery(updateMedicineQTY);
    }

    @Override
    public void deleteAllHaving_QTY0() {
        String query = "DELETE FROM products WHERE product_qty = 0";
        DBService.PreparedQuery(query);
    }

    @Override
    public List<Product> searchBybarcode() {
        List<Product> searchByBarcode = new ArrayList<>();
        try{
            ResultSet rs = DBService.query("SELECT * FROM products WHERE barcode  = "+ OrderMedicine.barcodeSearch);
            while (true) {
                assert rs != null;
                if (!rs.next())
                    break;
                searchByBarcode.add(new Product
                        (
                                Long.valueOf(rs.getString("barcode")),
                                rs.getString("product_name"),
                                rs.getString("product_varient"),
                                Double.valueOf(rs.getString("cost_price")),
                                Double.valueOf(rs.getString("sell_price")),
                                Integer.valueOf(rs.getString("product_qty"))));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(searchByBarcode);
        return  searchByBarcode;
    }





}
