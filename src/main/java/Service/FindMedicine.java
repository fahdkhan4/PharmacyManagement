package Service;

import Model.Product;
import dao.ProductDao;

import java.util.ArrayList;
import java.util.List;

public class FindMedicine implements GetAllMedicines {

    public String medicineName;

    public List<Product> getAllProducts_Data(){
        return ProductDao.getAllProducts();
    }

    public void getMedicineName(String medicineName){
        this.medicineName = medicineName;
    }
    public int getMatching_DataSize(){
        int count = (int) getAllProducts_Data().stream().filter(el->el.getM_name().equalsIgnoreCase(medicineName)).count();
        return count+1;
    }


    public Object [][] findMedicine_OnSearch(){
        int length = getAllProducts_Data().size();
        int j = 0;
        Object [][] products = new Object[getMatching_DataSize()][5];
        for (int i = 0; i < length; i++) {
            if(getAllProducts_Data().get(i).getM_name().equalsIgnoreCase(medicineName)){

                products[j][0] = getAllProducts_Data().get(i).getId();
                products[j][1] = getAllProducts_Data().get(i).getM_name();
                products[j][2] = getAllProducts_Data().get(i).getM_varient();
                products[j][3] = getAllProducts_Data().get(i).getPrice();
                products[j][4] = getAllProducts_Data().get(i).getQuantity();
                j +=1;
            }
        }
        return products;
    }


}
