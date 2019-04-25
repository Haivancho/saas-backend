package bg.unwe.saasbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import java.math.BigDecimal;


@Entity
@NamedQuery(name= Product.FIND_BY_PRODUCT_NAME, query ="SELECT p FROM Product p where p.name = :name")
@NamedQuery(name = Product.FIND_ALL_PRODUCTS, query = "SELECT p FROM Product p")
@NamedQuery(name = Product.DELETE_PRODUCT_BY_ID, query = "DELETE from  Product p where p.id = :id")
public class Product extends  AbstractEntity{

    public static final String FIND_BY_PRODUCT_NAME = "findByProductName";
    public static final String FIND_ALL_PRODUCTS = "findAllProducts";
    public static final String DELETE_PRODUCT_BY_ID = "deleteProductId";

    @Column(unique = true)
    private String name ;

    private String description;
    private BigDecimal price;


    public Product() {
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
