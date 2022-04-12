package Service;

import Model.SaleRecord;
import View.OrderMedicine;
import View.ViewSales;
import dao.ViewSales_Dao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.View;
import java.util.List;

public class SalesRecord {

    ViewSales_Dao sales_dao = new ViewSales_Dao();

    public List<SaleRecord> saleRecords(){
        return  sales_dao.gettingSalesRecord_DB();
    }

    public List<SaleRecord> filterByName(){
        return sales_dao.filterByName();
    }

    public List<SaleRecord> sortByProfit(){
        return sales_dao.sortByProfit();
    }

    public List<SaleRecord> filterByDate(){
        return sales_dao.filterByDate();
    }

    public Object [][] viewSaleRecord(){
        int size = saleRecords().size();
        Object [][] sales = new Object[size][7];
        for (int i = 0; i < size; i++) {
            sales [i][0] = saleRecords().get(i).getId();
            sales [i][1] = saleRecords().get(i).getOrder_id();
            sales [i][2] = saleRecords().get(i).getEmp_name();
            sales [i][3] = saleRecords().get(i).getOrder_date();
            sales [i][4] = saleRecords().get(i).getCost_price();
            sales [i][5] = saleRecords().get(i).getSell_price();
            sales [i][6] = saleRecords().get(i).getProfit();
        }
        return sales;
    }

    public DefaultTableModel filterSales_ByName(DefaultTableModel model, JTable viewSales_table){
        int size = (int) filterByName().stream().count();
        model = (DefaultTableModel)viewSales_table.getModel();
        Object [] sales = new Object[7];
        for (int i = 0; i < size; i++) {
            sales [0] = filterByName().get(i).getId();
            sales [1] = filterByName().get(i).getOrder_id();
            sales [2] = filterByName().get(i).getEmp_name();
            sales [3] = filterByName().get(i).getOrder_date();
            sales [4] = filterByName().get(i).getCost_price();
            sales [5] = filterByName().get(i).getSell_price();
            sales [6] = filterByName().get(i).getProfit();
            model.addRow(sales);
        }
        return model;
    }

    public DefaultTableModel filterSales_ByDate(DefaultTableModel model,JTable viewSales_table){
        int size = (int) filterByDate().stream().count();
        model = (DefaultTableModel) viewSales_table.getModel();
        Object [] sales = new Object[7];
        for (int i = 0; i < size; i++) {
            sales [0] = filterByDate().get(i).getId();
            sales [1] = filterByDate().get(i).getOrder_id();
            sales [2] = filterByDate().get(i).getEmp_name();
            sales [3] = filterByDate().get(i).getOrder_date();
            sales [4] = filterByDate().get(i).getCost_price();
            sales [5] = filterByDate().get(i).getSell_price();
            sales [6] = filterByDate().get(i).getProfit();
            model.addRow(sales);
        }
        return model;
    }

    public DefaultTableModel SortingBy_Profit(DefaultTableModel model,JTable viewSales_table){
        int size = (int) sortByProfit().stream().count();
        model = (DefaultTableModel) viewSales_table.getModel();
        Object [] sales = new Object[7];
        for (int i = 0; i < size; i++) {
            sales [0] = sortByProfit().get(i).getId();
            sales [1] = sortByProfit().get(i).getOrder_id();
            sales [2] = sortByProfit().get(i).getEmp_name();
            sales [3] = sortByProfit().get(i).getOrder_date();
            sales [4] = sortByProfit().get(i).getCost_price();
            sales [5] = sortByProfit().get(i).getSell_price();
            sales [6] = sortByProfit().get(i).getProfit();
            model.addRow(sales);
        }
        return model;
    }

    public DefaultTableModel viewAllSaleRecord(DefaultTableModel model,JTable viewSales_table){
        int size = saleRecords().size();
        model = (DefaultTableModel) viewSales_table.getModel();
        Object [] sales = new Object[7];
        for (int i = 0; i < size; i++) {
            sales [0] = saleRecords().get(i).getId();
            sales [1] = saleRecords().get(i).getOrder_id();
            sales [2] = saleRecords().get(i).getEmp_name();
            sales [3] = saleRecords().get(i).getOrder_date();
            sales [4] = saleRecords().get(i).getCost_price();
            sales [5] = saleRecords().get(i).getSell_price();
            sales [6] = saleRecords().get(i).getProfit();
            model.addRow(sales);
        }
        return model;
    }

    public Double totalRevenue(){
        Double totalAmount = 0.0;
        int size = saleRecords().size();
        for (int i = 0; i < size ; i++) {
            totalAmount += saleRecords().get(i).getProfit();
        }
        return totalAmount;
    }

}
