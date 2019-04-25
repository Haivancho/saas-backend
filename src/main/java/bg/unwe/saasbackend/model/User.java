package bg.unwe.saasbackend.model;

import javax.persistence.*;

@Entity
@NamedQuery(name = User.FIND_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username = :username")
@NamedQuery(name = User.FIND_ALL_USERS, query = "SELECT u FROM User u")
@NamedQuery(name = User.DELETE_USER_BY_ID, query = "DELETE FROM User u WHERE u.id = :id")
public class User extends AbstractEntity {

    public static final String FIND_BY_USERNAME = "findByUsername";
    public static final String FIND_ALL_USERS = "findAllUsers";
    public static final String DELETE_USER_BY_ID = "deleteUserById";


    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
