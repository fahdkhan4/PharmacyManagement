package Service;

import Model.Product;
import dao.Product_Dao;

import java.util.ArrayList;
import java.util.List;

public class UserCartProduct_Services {


    public List<Product> getCartProduct(){
        return  Product_Dao.getCart_Product();
    }
    

    public Object [][] getallUserCart_Product(){

        int size = (int) getCartProduct().stream().count();
        Object [][] cartProduct = new Object[size][5];
        for (int i = 0; i < size; i++) {
            cartProduct[i][0] =getCartProduct().get(i).getId();
            cartProduct[i][1] =getCartProduct().get(i).getMedicine_name();
            cartProduct[i][2] =getCartProduct().get(i).getMedicine_varient();
            cartProduct[i][3] =getCartProduct().get(i).getMedicine_price();
            cartProduct[i][4] =getCartProduct().get(i).getMedicine_quantity();
        }
        return cartProduct;
    }
}
