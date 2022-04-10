package Service;

import java.util.List;
import Model.*;
import dao.ProductFunctionality_Dao;
import dao.Product_Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductService implements GetAllMedicines{


    ProductFunctionality_Dao  product_dao = new ProductFunctionality_Dao();

    public List<Product> getAllProducts_Data(){
        return Product_Dao.getAllProducts();
    }

//                                                                      solution of updating the 0 after exit button
    public List<Product> getAllProductFor_Update(){
        return Product_Dao.getAllProductsZerosALSO();
    }

    public List<Product> getMedicineByBarCode(){
        return product_dao.searchBybarcode();
    }

    public Object[][] getAllMedicines(){
        int count = (int) getAllProducts_Data().stream().count();
        Object [][] data = new Object[count][5];
        for (int i = 0; i < count ; i++) {
            data[i][0] = getAllProducts_Data().get(i).getBarCode();
            data[i][1] = getAllProducts_Data().get(i).getMedicine_name();
            data[i][2] = getAllProducts_Data().get(i).getMedicine_varient();
            data[i][3] = getAllProducts_Data().get(i).getMedicine_Saleprice();
            data[i][4] = getAllProducts_Data().get(i).getMedicine_quantity();
        }
        return data;
    }
    public DefaultTableModel addingData(DefaultTableModel model, JTable ordermedicinetable){
        int count = (int)  getAllProducts_Data().stream().count();
        model = (DefaultTableModel) ordermedicinetable.getModel();
        Object  [] array = new Object[5];
        for (int i = 0; i < count; i++) {
            array[0] =  getAllProducts_Data().get(i).getBarCode();
            array[1] =  getAllProducts_Data().get(i).getMedicine_name();
            array[2] =  getAllProducts_Data().get(i).getMedicine_varient();
            array[3] =  getAllProducts_Data().get(i).getMedicine_Saleprice();
            array[4] =  getAllProducts_Data().get(i).getMedicine_quantity();
            model.addRow(array);
        }
        return model;
    }

    public Object[][] getAllMedicineForUpdate(){
        int count = (int) getAllProductFor_Update().stream().count();
        Object [][] data = new Object[count][5];
        for (int i = 0; i < count ; i++) {
            data[i][0] = getAllProductFor_Update().get(i).getBarCode();
            data[i][1] = getAllProductFor_Update().get(i).getMedicine_name();
            data[i][2] = getAllProductFor_Update().get(i).getMedicine_varient();
            data[i][3] = getAllProductFor_Update().get(i).getMedicine_Saleprice();
            data[i][4] = getAllProductFor_Update().get(i).getMedicine_quantity();
        }
        return data;
    }

    public DefaultTableModel getMedicineByBarcode(DefaultTableModel model, JTable ordermedicinetable){
        int count = (int)  getMedicineByBarCode().stream().count();
        model = (DefaultTableModel) ordermedicinetable.getModel();
        Object  [] array = new Object[5];
        for (int i = 0; i < count; i++) {
            array[0] =  getMedicineByBarCode().get(i).getBarCode();
            array[1] =  getMedicineByBarCode().get(i).getMedicine_name();
            array[2] =  getMedicineByBarCode().get(i).getMedicine_varient();
            array[3] =  getMedicineByBarCode().get(i).getMedicine_Saleprice();
            array[4] =  getMedicineByBarCode().get(i).getMedicine_quantity();
            model.addRow(array);
        }
        return model;
    }

//                                                              finding the quantity of the specific barcode
    public Integer getmedicineQuantityBy_Barcode(Long barcode){
        Integer quantity = 0 ;
        for (Product product:getMedicineByBarCode()) {
            if(product.getBarCode().equals(barcode)){
                quantity += product.getMedicine_quantity();
            }
        }
        return quantity;
    }

}
