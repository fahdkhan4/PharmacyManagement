package Model;

import java.time.LocalDate;


public class OrderProduct_Model {
    private String employee_name;
    private LocalDate order_date;
    private String state;


    public OrderProduct_Model() {
    }

    public OrderProduct_Model(String employee_name, LocalDate order_date, String state) {

        this.employee_name = employee_name;
        this.order_date = order_date;
        this.state = state;
    }

    @Override
    public String toString() {
        return "OrdeProduct_Model{" +
                ", employee_name='" + employee_name + '\'' +
                ", order_date=" + order_date +
                ", state='" + state + '\'' +
                '}';
    }


    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public LocalDate getOrder_date() {
        return order_date;
    }

    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
