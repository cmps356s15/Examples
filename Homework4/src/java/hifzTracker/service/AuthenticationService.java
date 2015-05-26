package hifzTracker.service;

import hifzTracker.entity.Credentials;
import hifzTracker.entity.User;
import hifzTracker.repository.UserRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Stateless
public class AuthenticationService {

    @Inject
    UserRepository userRepository;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response login(Credentials credentials) {
        User user = userRepository.getUser(credentials.getUsername(), credentials.getPassword());
        System.out.println("Login Service - UserId : " + user.getId());
        if (user != null) {
            return Response.ok(user).build();
        } else {
            String msg = "Invalid username and/or password";
            return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
        }
    }
}