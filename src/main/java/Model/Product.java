package Model;

public class Product {
    private Long id;
    private String medicine_name;
    private String medicine_varient;
    private Double medicine_price;
    private Integer medicine_quantity;

    public Product(){}

    public Product(Long id, String medicine_name, String medicine_varient, Double medicine_price, Integer medicine_quantity) {
        this.id = id;
        this.medicine_name = medicine_name;
        this.medicine_varient = medicine_varient;
        this.medicine_price = medicine_price;
        this.medicine_quantity = medicine_quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public void setMedicine_name(String medicine_name) {
        this.medicine_name = medicine_name;
    }

    public String getMedicine_varient() {
        return medicine_varient;
    }

    public void setMedicine_varient(String medicine_varient) {
        this.medicine_varient = medicine_varient;
    }

    public Double getMedicine_price() {
        return medicine_price;
    }

    public void setMedicine_price(Double medicine_price) {
        this.medicine_price = medicine_price;
    }

    public Integer getMedicine_quantity() {
        return medicine_quantity;
    }

    public void setMedicine_quantity(Integer medicine_quantity) {
        this.medicine_quantity = medicine_quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", medicine_name='" + medicine_name + '\'' +
                ", medicine_varient='" + medicine_varient + '\'' +
                ", medicine_price=" + medicine_price +
                ", medicine_quantity=" + medicine_quantity +
                '}';
    }


}

//owner_name, car_name,bookinmg,owner_commision,
//Find owner name whose car is not booked