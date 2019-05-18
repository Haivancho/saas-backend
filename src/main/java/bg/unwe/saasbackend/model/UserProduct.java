package bg.unwe.saasbackend.model;

import javax.persistence.Entity;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class UserProduct extends AbstractEntity {

    @MapsId
    @OneToOne
    private User user;

    @MapsId
    @OneToOne
    private Product product;

    public UserProduct() {}

    public UserProduct(User user, Product product) {
        this.user = user;
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
