package com.pharmacymanagement.dao;

import com.pharmacymanagement.Model.ProductCart_Model;
import com.pharmacymanagement.Model.SaleRecord;

import java.util.List;

public interface Sales_Dao {

//                                                      getting sales record

    void insertingSalesRecord();

    List<SaleRecord> gettingSalesRecord_DB();

    List<SaleRecord> filterByName();

    List<SaleRecord> filterByDate();

    List<SaleRecord> sortByProfit();

    List<ProductCart_Model> gettingSalesDetails();
}
