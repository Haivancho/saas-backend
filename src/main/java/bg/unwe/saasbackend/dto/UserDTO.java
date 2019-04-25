package bg.unwe.saasbackend.dto;

import bg.unwe.saasbackend.model.Role;
import bg.unwe.saasbackend.model.User;

import javax.validation.constraints.NotBlank;

public class UserDTO {
    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private Role role;

    public UserDTO() {}

    public UserDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        role = user.getRole();
    }

    public UserDTO(Long id, String username, String password, Role role) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
