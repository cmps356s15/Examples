/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hifzTracker.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hifzTracker.entity.Surah;
import hifzTracker.entity.Task;
import hifzTracker.repository.SurahRepository;
import hifzTracker.repository.TaskRepository;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.hibernate.validator.constraints.URL;

/**
 *
 * @author taofik
 */
@Path("/surahs")
@Stateless
public class SurahService {
    
    @Inject
    SurahRepository surahRepository;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingTasks(){
      
        List<Surah> pendingTasks = surahRepository.getSurahs();
        Gson gson = new Gson();
         
        String tasksJson = gson.toJson(pendingTasks);
        return Response.ok(tasksJson).build();  
    } 
}
