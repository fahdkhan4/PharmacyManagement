package Model;

public class Product {
    private Long barCode;
    private String medicine_name;
    private String medicine_varient;
    private Double medicine_Costprice;
    private Double medicine_Saleprice;
    private Integer medicine_quantity;

    public Product(){}

    public Product(Long barCode, String medicine_name, String medicine_varient, Double medicine_Costprice, Double medicine_Saleprice, Integer medicine_quantity) {
        this.barCode = barCode;
        this.medicine_name = medicine_name;
        this.medicine_varient = medicine_varient;
        this.medicine_Costprice = medicine_Costprice;
        this.medicine_Saleprice = medicine_Saleprice;
        this.medicine_quantity = medicine_quantity;
    }

    public Long getBarCode() {
        return barCode;
    }

    public void setId(Long barCode) {
        this.barCode = barCode;
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

    public Double getMedicine_Costprice() {
        return medicine_Costprice;
    }

    public void setMedicine_Costprice(Double medicine_Costprice) {
        this.medicine_Costprice = medicine_Costprice;
    }

    public Double getMedicine_Saleprice() {
        return medicine_Saleprice;
    }

    public void setMedicine_Saleprice(Double medicine_Saleprice) {
        this.medicine_Saleprice = medicine_Saleprice;
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
                "barCode=" + barCode +
                ", medicine_name='" + medicine_name + '\'' +
                ", medicine_varient='" + medicine_varient + '\'' +
                ", medicine_Costprice=" + medicine_Costprice +
                ", medicine_Saleprice=" + medicine_Saleprice +
                ", medicine_quantity=" + medicine_quantity +
                '}';
    }
}

//owner_name, car_name,bookinmg,owner_commision,
//Find owner name whose car is not booked