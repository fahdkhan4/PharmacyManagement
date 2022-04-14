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
    public void insertProduct_ToDB(Product product)  {
        try {
            String query = "INSERT INTO products(barcode,product_name,product_varient,cost_price,sell_price,product_qty) VALUES ("+product.getBarCode()+",'"+product.getMedicine_name()+"','"+product.getMedicine_varient()+"',"+product.getMedicine_Costprice()+","+product.getMedicine_Saleprice()+","+product.getMedicine_quantity()+")";
            DBService.addProductPreparedQuery(query);
            DBService.con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete_Medicines(HashSet<Long> productCode) {
        String query ;
        for (Long code:productCode) {
            query = "DELETE FROM products  WHERE barcode="+code;
            try {
                DBService.deleteProduct(query);
                DBService.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateMedicine(Product updateProduct){

        String updateProduct_query = "UPDATE products SET product_name = '"+updateProduct.getMedicine_name()+"'," +
                "product_varient = '"+updateProduct.getMedicine_varient()+"', sell_price= '"+updateProduct.getMedicine_Saleprice()+"'," +
                "product_qty = '"+updateProduct.getMedicine_quantity()+"' where barcode = "+updateProduct.getBarCode();
        try {
            DBService.PreparedQuery(updateProduct_query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//                                                                    update medicine quantity for mouse click
    @Override
    public void updateMedicine_Quantity(Product updateProductQTY) {
        String updateMedicineQTY = "UPDATE products SET product_qty = "+updateProductQTY.getMedicine_quantity()+" WHERE barcode ="+updateProductQTY.getBarCode();
        try {
            DBService.PreparedQuery(updateMedicineQTY);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//                                                                      update medicine quantity for barcode scanner
    @Override
    public void updateMedicineQuantity_Barcode(Long barcode,Integer productQTY) {
        String updateQuantity = "UPDATE products SET product_qty = "+productQTY+" WHERE barcode = "+barcode;
        try {
            DBService.PreparedQuery(updateQuantity);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//                                                                      get product quantity of barcode scanner product
    @Override
    public int getProductQuantityOf_barcodeScanner(Long barcode,Integer quantity) {
        int productQuantity =  quantity;
        for (int i = 0; i < Product_Dao.getAllProducts().size(); i++) {
            if(Product_Dao.getAllProducts().get(i).getBarCode().equals(barcode)){
                productQuantity += Product_Dao.getAllProducts().get(i).getMedicine_quantity();
            }
        }
        return productQuantity;
    }

    @Override
    public void updateMedicineQuantity_AfterRemovingFromCart(Long barcode, Integer quantity) {
        String query = "UPDATE products SET product_qty = "+quantity+" WHERE barcode = "+barcode;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            DBService.con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  searchByBarcode;
    }




}
