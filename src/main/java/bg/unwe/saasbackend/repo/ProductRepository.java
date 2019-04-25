package bg.unwe.saasbackend.repo;



import bg.unwe.saasbackend.model.Product;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductRepository {

    @Inject
    EntityManager entityManager;

    public Product save(Product product){
        if(product.getId() == null || product.getId() ==0){
            entityManager.persist(product);
        }else {
            product = entityManager.merge(product);
        }
        return product;
    }

    public List<Product> getAllProducts() {
        Query query = entityManager.createNamedQuery(Product.FIND_ALL_PRODUCTS);
        return query.getResultList();
    }

    public Optional<Product> getProductById(Long id){
        return Optional.ofNullable(entityManager.find(Product.class, id));
    }

    public Optional<Product> getProductByName (String name){
        Query query = entityManager.createNamedQuery(Product.FIND_BY_PRODUCT_NAME);
        query.setParameter("name" , name);
        return query.getResultList().stream().findFirst();
    }

    public void deleteProduct(Long id ){
        Query query = entityManager.createNamedQuery(Product.DELETE_PRODUCT_BY_ID);
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
