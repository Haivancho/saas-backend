package bg.unwe.saasbackend.dto;

import bg.unwe.saasbackend.model.Product;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private BigDecimal price;

    public ProductDTO() {
    }

    public ProductDTO(Product product){
        id=product.getId();
        name=product.getName();
        description=product.getDescription();
        price=product.getPrice();
    }

    public ProductDTO(Long id, @NotBlank String name, @NotBlank String description, @NotBlank BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
