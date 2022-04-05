package Model;

public class ProductCart_Model {

    private Long product_code;
    private String product_name;
    private String product_varient;
    private Double product_price;
    private Double price_unit;
    private Integer product_quantity;
    private Integer order_id;

    public ProductCart_Model(Long product_code, String product_name, String product_varient, Double product_price, Double price_unit, Integer product_quantity, Integer order_id) {
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_varient = product_varient;
        this.product_price = product_price;
        this.price_unit = price_unit;
        this.product_quantity = product_quantity;
        this.order_id = order_id;
    }

    public Long getProduct_code() {
        return product_code;
    }

    public void setProduct_code(Long product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_varient() {
        return product_varient;
    }

    public void setProduct_varient(String product_varient) {
        this.product_varient = product_varient;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Double getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(Double price_unit) {
        this.price_unit = price_unit;
    }

    public Integer getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.product_quantity = product_quantity;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "ProductCart_Model{" +
                "product_code=" + product_code +
                ", product_name='" + product_name + '\'' +
                ", product_varient='" + product_varient + '\'' +
                ", product_price=" + product_price +
                ", price_unit=" + price_unit +
                ", product_quantity=" + product_quantity +
                ", order_id=" + order_id +
                '}';
    }
}
