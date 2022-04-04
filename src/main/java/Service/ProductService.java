package Service;

import java.util.List;
import Model.*;
import dao.Product_Dao;

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

}
