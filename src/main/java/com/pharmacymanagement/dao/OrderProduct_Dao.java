package com.pharmacymanagement.dao;

import com.pharmacymanagement.Model.OrderProduct_Model;

public interface OrderProduct_Dao {

//    List<OrderProduct_Model> getOrderProduct();

//                                                                              insert order information
    void inserting_OrderInformation(OrderProduct_Model productmodel);

//                                                                              update order information
    void update_OrderInformation(OrderProduct_Model updateproduct);

//                                                                              delete order information
    void delete_OrderInformation();



}
