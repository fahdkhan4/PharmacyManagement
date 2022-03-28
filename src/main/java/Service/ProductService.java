package Service;

import dao.ProductDao;
import java.util.List;
import Model.*;

public class ProductService implements GetAllMedicines{

    public List<Product> getAllProducts_Data(){
        return ProductDao.getAllProducts();
    }

    public Object[][] getAllMedicines(){
        int count = (int) getAllProducts_Data().stream().count();
        Object [][] data = new Object[count][5];
        for (int i = 0; i < count ; i++) {
            data[i][0] = getAllProducts_Data().get(i).getId();
            data[i][1] = getAllProducts_Data().get(i).getM_name();
            data[i][2] = getAllProducts_Data().get(i).getM_varient();
            data[i][3] = getAllProducts_Data().get(i).getPrice();
            data[i][4] = getAllProducts_Data().get(i).getQuantity();
        }
        return data;
    }

}
