package products;

public class Product {
    private Integer id;
    private String m_name;
    private String m_varient;
    private Double price;
    private Integer quantity;

    public Product(Integer id, String m_name, String m_varient, Double price, Integer quantity) {
        this.id = id;
        this.m_name = m_name;
        this.m_varient = m_varient;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", m_name='" + m_name + '\'' +
                ", m_varient='" + m_varient + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getM_varient() {
        return m_varient;
    }

    public void setM_varient(String m_varient) {
        this.m_varient = m_varient;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //    private Long id;
//    private String name;
//    private Long categoryId;
//    private Long price;
//
//    public Product(Long id, String name, Long categoryId, Long price) {
//        this.id = id;
//        this.name = name;
//        this.categoryId = categoryId;
//        this.price = price;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Long categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Long getPrice() {
//        return price;
//    }
//
//    public void setPrice(Long price) {
//        this.price = price;
//    }
//
//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", categoryId=" + categoryId +
//                ", price=" + price +
//                '}';
//    }
}
