package Model;

import java.time.LocalDate;

public class Invoice {
//
    private Integer order_id;
    private String emp_name;
    private LocalDate date;


    public Invoice( Integer order_id, String emp_name, LocalDate date) {

        this.order_id = order_id;
        this.emp_name = emp_name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                ", order_id=" + order_id +
                ", emp_name='" + emp_name + '\'' +
                ", date=" + date +
                '}';
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
