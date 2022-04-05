package Model;

public class Invoice_line {

    private Integer invoice_id;
    private Integer order_id;
    private String item_name;
    private Double item_price;
    private Integer item_qty;
    private String item_varient;

    public Invoice_line(Integer invoice_id, Integer order_id, String item_name, Double item_price, Integer item_qty, String item_varient) {
        this.invoice_id = invoice_id;
        this.order_id = order_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.item_qty = item_qty;
        this.item_varient = item_varient;
    }

    @Override
    public String toString() {
        return "Invoice_line{" +
                "invoice_id=" + invoice_id +
                ", order_id=" + order_id +
                ", item_name='" + item_name + '\'' +
                ", item_price=" + item_price +
                ", item_qty=" + item_qty +
                ", item_varient='" + item_varient + '\'' +
                '}';
    }

    public Integer getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(Integer invoice_id) {
        this.invoice_id = invoice_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Double getItem_price() {
        return item_price;
    }

    public void setItem_price(Double item_price) {
        this.item_price = item_price;
    }

    public Integer getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(Integer item_qty) {
        this.item_qty = item_qty;
    }

    public String getItem_varient() {
        return item_varient;
    }

    public void setItem_varient(String item_varient) {
        this.item_varient = item_varient;
    }
}
