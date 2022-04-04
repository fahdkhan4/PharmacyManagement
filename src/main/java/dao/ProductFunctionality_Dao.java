package dao;

import Model.Product;

import java.util.HashSet;

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


}
