package bg.unwe.saasbackend.repo;

import bg.unwe.saasbackend.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepository {

    @Inject
    EntityManager em;

    public User save(User user) {
        if (user.getId() == null || user.getId() == 0) {
            em.persist(user);
        } else {
            user = em.merge(user);
        }

        return user;
    }

    public List<User> getAllUsers() {
        Query query = em.createNamedQuery(User.FIND_ALL_USERS);
        return query.getResultList();
    }

    public Optional<User> getUserById(Long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    public Optional<User> getUserByUsername(String username) {
        Query query = em.createNamedQuery(User.FIND_BY_USERNAME);
        query.setParameter("username", username);

        return query.getResultList().stream().findFirst();
    }

    public void deleteUser(Long id) {
        Query query = em.createNamedQuery(User.DELETE_USER_BY_ID);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
