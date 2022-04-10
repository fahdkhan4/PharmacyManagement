package Service;

import Model.ProductCart_Model;
import dao.UserCartProduct_Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class UserCartProduct_Services {


    public static Integer  cartProductquantity;
    public static Double cartProductprice;


    public List<ProductCart_Model> getCartProduct(){
        return  UserCartProduct_Dao.getCart_Product();
    }
    

    public Object [][] getallUserCart_Product(){

        int size = (int) getCartProduct().stream().count();
        Object [][] cartProduct = new Object[size][6];
        for (int i = 0; i < size; i++) {
            cartProduct[i][0] =getCartProduct().get(i).getProduct_code();
            cartProduct[i][1] =getCartProduct().get(i).getProduct_name();
            cartProduct[i][2] =getCartProduct().get(i).getProduct_varient();
            cartProduct[i][3] =getCartProduct().get(i).getProduct_price();
            cartProduct[i][4] =getCartProduct().get(i).getProduct_quantity();
            cartProduct[i][5] =getCartProduct().get(i).getPrice_unit();
    }
        return cartProduct;
    }

    public DefaultTableModel cartData(DefaultTableModel model, JTable userorder_table){

        int size = (int) getCartProduct().stream().count();
         model = (DefaultTableModel) userorder_table.getModel();
        Object  [] array = new Object[6];
        for (int i = 0; i < size; i++) {
            array[0] =getCartProduct().get(i).getProduct_code();
            array[1] =getCartProduct().get(i).getProduct_name();
            array[2] =getCartProduct().get(i).getProduct_varient();
            array[3] =getCartProduct().get(i).getProduct_price();
            array[4] =getCartProduct().get(i).getProduct_quantity();
            array[5] =getCartProduct().get(i).getPrice_unit();
            model.addRow(array);
        }
        return model;
    }


    public Boolean checkDuplicateInCart(ProductCart_Model cartmodel){
        Boolean checkForvalue = false;
        cartProductquantity = 0;
        cartProductprice = 0.0;

        for (int i = 0; i < getCartProduct().size(); i++) {
            if(getCartProduct().get(i).getProduct_code().equals(cartmodel.getProduct_code())){
                cartProductquantity = getCartProduct().get(i).getProduct_quantity() + cartmodel.getProduct_quantity();
                cartProductprice = getCartProduct().get(i).getPrice_unit() + cartmodel.getPrice_unit();
                checkForvalue = true;
            }
        }
        return checkForvalue;
    }

    public Boolean checkDuplicateIncart_ByBarcode(Long barcode){
        Boolean checkForduplicateValue = false;

        for (int i = 0; i < getCartProduct().size(); i++) {
           if(getCartProduct().get(i).getProduct_code().equals(barcode)){
               checkForduplicateValue = true;
           }
        }
        return checkForduplicateValue;
    }

}
