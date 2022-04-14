package Service;

import Model.ProductCart_Model;
import dao.ViewSales_Dao;

import java.util.List;

public class SalesDetails_ShowOID {
    ViewSales_Dao sale_dao = new ViewSales_Dao();

    public List<ProductCart_Model> salesRecords(){
        return  sale_dao.gettingSalesDetails();
    }


    public Object[][] saleDetailsBy_OrderID(){
        int size = (int) salesRecords().stream().count();
        Object [][] details = new Object[size][6];
        for (int i = 0; i < size ; i++) {
            details [i][0] = salesRecords().get(i).getProduct_code();
            details [i][1] = salesRecords().get(i).getProduct_name();
            details [i][2] = salesRecords().get(i).getProduct_varient();
            details [i][3] = salesRecords().get(i).getProduct_price();
            details [i][4] = salesRecords().get(i).getProduct_quantity();
            details [i][5] = salesRecords().get(i).getPrice_unit();
        }
        return details;
    }
}
