package Service;

import Model.Product;

import java.util.ArrayList;

public class UserCartProduct_Services {



    public Object [][] getallUserCart_Product(ArrayList<Product> userCart_Details){

        int size = (int) userCart_Details.stream().count();
        Object [][] cartProduct = new Object[size][5];
        for (int i = 0; i < size; i++) {
            cartProduct[i][0] =userCart_Details.get(i).getId();
            cartProduct[i][1] =userCart_Details.get(i).getM_name();
            cartProduct[i][2] =userCart_Details.get(i).getM_varient();
            cartProduct[i][3] =userCart_Details.get(i).getPrice();
            cartProduct[i][4] =userCart_Details.get(i).getQuantity();
        }
        return cartProduct;
    }
}
