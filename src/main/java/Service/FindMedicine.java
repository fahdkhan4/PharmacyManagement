package Service;

import Model.Product;
import dao.Product_Dao;

import java.util.ArrayList;
import java.util.List;

public class FindMedicine implements GetAllMedicines {

    public static String medicineName;

    public List<Product> getAllProducts_Data(){
        return Product_Dao.getAllProducts();
    }

    public void getMedicineName(String medicineName){
        this.medicineName = medicineName;
    }

    public List<Product> searchMedicine(){
        ArrayList<Product> resultMedicine = new ArrayList<>();
        for (Product product:getAllProducts_Data()) {
            if(medicineName.equalsIgnoreCase(product.getMedicine_name())){
                resultMedicine.add(product);
            }
        }
        return resultMedicine;
    }

    public  int size_OfSearchMedicine(){
        int size = searchMedicine().size();
        return size;
    }


    public Object [][] findMedicine_OnSearch(){
        int length = searchMedicine().size();
        System.out.println(searchMedicine());
        Object [][] products = new Object[length][5];
        for (int i = 0; i < length; i++) {
//            if(searchMedicine().get(i).getMedicine_name().equalsIgnoreCase(medicineName)){
                products[i][0] = searchMedicine().get(i).getBarCode();
                products[i][1] = searchMedicine().get(i).getMedicine_name();
                products[i][2] = searchMedicine().get(i).getMedicine_varient();
                products[i][3] = searchMedicine().get(i).getMedicine_Saleprice();
                products[i][4] = searchMedicine().get(i).getMedicine_quantity();
//            }
        }
        return products;
    }




}
