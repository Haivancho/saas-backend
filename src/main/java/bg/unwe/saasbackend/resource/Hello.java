package bg.unwe.saasbackend.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/hello")
public class Hello {

    @GET
    public Response sayHello() {
        return Response.ok("Hello!").build();
    }
}
