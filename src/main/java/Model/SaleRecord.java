package Model;

import java.time.LocalDate;

public class SaleRecord {
    private Integer id;
    private Integer order_id;
    private String emp_name;
    private LocalDate order_date;
    private Double cost_price;
    private Double sell_price;
    private Double profit;

    public SaleRecord(Integer id, Integer order_id, String emp_name, LocalDate order_date, Double cost_price, Double sell_price, Double profit) {
        this.id = id;
        this.order_id = order_id;
        this.emp_name = emp_name;
        this.order_date = order_date;
        this.cost_price = cost_price;
        this.sell_price = sell_price;
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "SaleRecord{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", emp_name='" + emp_name + '\'' +
                ", order_date=" + order_date +
                ", cost_price=" + cost_price +
                ", sell_price=" + sell_price +
                ", profit=" + profit +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public Double getCost_price() {
        return cost_price;
    }

    public void setCost_price(Double cost_price) {
        this.cost_price = cost_price;
    }

    public Double getSell_price() {
        return sell_price;
    }

    public void setSell_price(Double sell_price) {
        this.sell_price = sell_price;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }
}
