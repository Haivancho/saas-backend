package bg.unwe.saasbackend.resource;

import bg.unwe.saasbackend.dto.UserDTO;
import bg.unwe.saasbackend.model.User;
import bg.unwe.saasbackend.repo.UserRepository;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Path("/users")
public class UserResource implements CRUDResource<UserDTO> {

    @Inject
    private UserRepository userRepository;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response create(@Valid UserDTO userDTO) {
        if (userRepository.getUserByUsername(userDTO.getUsername()).isPresent()) {
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            User user = userRepository.save(new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole()));
            return Response.ok(new UserDTO(user.getId(), user.getUsername(), null, user.getRole())).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getAll() {
        List<User> allUsers = userRepository.getAllUsers();

        UserDTO[] users = new UserDTO[allUsers.size()];

        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            users[i] = new UserDTO(user.getId(), user.getUsername(), null, user.getRole());
        }

        return Response.ok(users).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    @Override
    public Response getById(@PathParam("id") Long id) {
        Optional<User> user = userRepository.getUserById(id);

        if (user.isPresent()) {
            return Response.ok(new UserDTO(user.get())).build();

        } else return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    @Override
    public Response update(UserDTO userDTO) {

        Optional<User> optUser = userRepository.getUserById(userDTO.getId());

        if (optUser.isPresent()) {
            User user = optUser.get();

            if (userDTO.getPassword() != null) {
                user.setPassword(userDTO.getPassword());
            }

            if (userDTO.getRole() != null) {
                user.setRole(userDTO.getRole());
            }

            return Response.ok(new UserDTO(user)).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    @Override
    public Response delete(@PathParam("id") Long id) {
        userRepository.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
