package com.pharmacymanagement.dao;

import com.pharmacymanagement.Model.OrderProduct_Model;

import java.sql.SQLException;

public class OrderProduct_Functionality implements OrderProduct_Dao {



    @Override
    public void inserting_OrderInformation(OrderProduct_Model productmodel) {
        String query = "INSERT INTO productorder(user_name,order_date,state) VALUES ('"+productmodel.getEmployee_name()+"','"+productmodel.getOrder_date()+"','"+productmodel.getState()+"')";
        try {
            DBService.PreparedQuery_GettingKey(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update_OrderInformation(OrderProduct_Model updateproduct) {
        String query = "UPDATE productorder SET  user_name = '"+updateproduct.getEmployee_name()+"', state = '"+updateproduct.getState()+"' WHERE id = "+DBService.orderID;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete_OrderInformation()  {
        String query = "DELETE FROM productorder WHERE id ="+DBService.orderID;
        try {
            DBService.PreparedQuery(query);
            DBService.con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
