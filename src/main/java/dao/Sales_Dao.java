package dao;

import Model.SaleRecord;

import java.util.List;

public interface Sales_Dao {

//                                                      getting sales record

    void insertingSalesRecord();

    List<SaleRecord> gettingSalesRecord_DB();

    List<SaleRecord> filterByName();

    List<SaleRecord> filterByDate();

}
