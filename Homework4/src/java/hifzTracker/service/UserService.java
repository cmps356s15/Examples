/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hifzTracker.service;

import com.google.gson.Gson;
import hifzTracker.entity.User;
import hifzTracker.repository.UserRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author taofik
 */
@Path("/user")
@Stateless
public class UserService {
    
    @Inject
    UserRepository userRepository;
    
    //text plain because i was getting an error when using APPLICATION_JSON
    @POST
    @Consumes({MediaType.TEXT_PLAIN})
    public Response login(String userStr){
        
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        User AuthUser;
        AuthUser = userRepository.getUser(userStr.split("-")[0], userStr.split("-")[1]);
        
        if (AuthUser != null){
            Gson gson = new Gson();
            String userJson = gson.toJson(AuthUser);
            return Response.ok(userJson).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).entity("Failed to login!").build(); 
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUsers(){
       
       Gson gson = new Gson();
       
       return Response.ok(gson.toJson(userRepository.getUsers())).build();
        
    }
    
}
