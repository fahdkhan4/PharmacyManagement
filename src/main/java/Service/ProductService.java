package Service;

import java.util.List;
import Model.*;
import View.OrderMedicine;
import dao.Product_Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ProductService implements GetAllMedicines{


    public List<Product> getAllProducts_Data(){
        return Product_Dao.getAllProducts();
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
        int count = (int) getAllProducts_Data().stream().count();
        model = (DefaultTableModel) ordermedicinetable.getModel();
        Object  [] array = new Object[count];
        for (int i = 0; i < count; i++) {
            array[0] = getAllProducts_Data().get(i).getBarCode();
            array[1] = getAllProducts_Data().get(i).getMedicine_name();
            array[2] = getAllProducts_Data().get(i).getMedicine_varient();
            array[3] = getAllProducts_Data().get(i).getMedicine_Saleprice();
            array[4] = getAllProducts_Data().get(i).getMedicine_quantity();
            model.addRow(array);
        }
        return model;
    }


}
