package Service;

import Model.Product;
import Model.ProductCart_Model;
import dao.OrderProduct_Dao;
import dao.UserCartProduct_Dao;

import java.util.List;

public class UserCartProduct_Services {


    public Double totalamount = 0.0;
    Double totalmedicineAmount = 0.0;


    public List<ProductCart_Model> getCartProduct(){
        return  UserCartProduct_Dao.getCart_Product();
    }
    

    public Object [][] getallUserCart_Product(){

        int size = (int) getCartProduct().stream().count();
        Object [][] cartProduct = new Object[size][5];
        for (int i = 0; i < size; i++) {
            cartProduct[i][0] =getCartProduct().get(i).getProduct_code();
            cartProduct[i][1] =getCartProduct().get(i).getProduct_name();
            cartProduct[i][2] =getCartProduct().get(i).getProduct_varient();
            cartProduct[i][3] =getCartProduct().get(i).getPrice_unit();
            cartProduct[i][4] =getCartProduct().get(i).getProduct_quantity();
        }
        return cartProduct;
    }
    public Double totalMedicine_Amount(){
        for (int i = 0; i < getCartProduct().size(); i++) {

            totalamount = getCartProduct().get(i).getProduct_quantity() * getCartProduct().get(i).getPrice_unit();
        }
        totalmedicineAmount += totalamount;
        return totalmedicineAmount;
    }

}
