package dao;

import Model.OrderProduct_Model;
import Model.Product;
import Model.ProductCart_Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderProduct_Dao {

//    List<OrderProduct_Model> getOrderProduct();

//                                                                              insert order information
    void inserting_OrderInformation(OrderProduct_Model productmodel);

//                                                                              update order information
    void update_OrderInformation(OrderProduct_Model updateproduct);

//                                                                              delete order information
    void delete_OrderInformation();



}
