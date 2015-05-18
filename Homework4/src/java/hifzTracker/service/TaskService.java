/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hifzTracker.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hifzTracker.entity.Task;
import hifzTracker.repository.TaskRepository;
import java.net.URI;
import java.net.URISyntaxException;
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
@Path("/task")
@Stateless
public class TaskService {
    
    @Inject
    TaskRepository taskRepository;
    
    @GET
    @Path("{uid}/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingTasks(@PathParam("uid") int userID){
        
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Task> pendingTasks = taskRepository.getPendingTasks(userID);
        Gson gson = new Gson();
        GsonBuilder gb = new GsonBuilder();
        gb.setDateFormat(null);
        
        String tasksJson = gson.toJson(pendingTasks);
        
        return Response.ok(tasksJson).build();
        
    }
    
    @GET
    @Path("{uid}/pending/{tid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingTask(@PathParam("uid") int userID, @PathParam("tid") int taskID ){
        
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Task pendingTask = taskRepository.getTask(userID, taskID);
        Gson gson = new Gson();
       
        String tasksJson = gson.toJson(pendingTask);
        
        return Response.ok(tasksJson).build();
        
    }
    
    @GET
    @Path("{uid}/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedTasks(@PathParam("uid") int userID){
        List<Task> completedTasks = taskRepository.getCompletedTasks(userID);
        Gson gson = new Gson();
        String tasksJson = gson.toJson(completedTasks);
        
        return Response.ok(tasksJson).build();
    }
    
    @PUT
    @Path("/{uid}/complete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response completeTask(@PathParam("uid") int userID, Task task){
        
        Gson gson = new Gson();
        
        String temp = gson.toJson(task);
     
        taskRepository.completeTask(userID, task.getId(), task.getCompletedDate(), task.getLevel(), task.getComment());      
        System.err.println("USER ID: " + userID);       
        return Response.ok(temp).build();
    }
    
    @DELETE
    @Path("/{uid}/{tid}")
    public Response deleteTask(@PathParam("uid") int userID, @PathParam("tid") int taskID){
        taskRepository.deleteTask(userID, taskID);
        
        return Response.ok("Deleted!").build();
    }
    
    @POST
    @Path("pending/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTask(@PathParam("id") int userID, Task task){
        try {
            int taskID = taskRepository.addTask(userID, task);
            String location = String.format("/hifz/api/task/%s/pending/%s", userID, taskID);
           // String msg = String.format("contact #%d created successfully", taskID);
            return Response.created(new URI(location)).build();
        } catch (Exception ex) {
           // String msg = String.format("Adding contact failed because : %s",
                  //  ex.getCause().getMessage());
            return Response.serverError().build();
        }
      
    }
    
    @PUT
    @Path("/{uid}/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(@PathParam("uid") int userID, Task task){
        
        Gson gson = new Gson();
        
        String temp = gson.toJson(task);
     
        taskRepository.updateTask(userID, task);
        System.err.println("USER ID: " + userID);       
        return Response.ok(temp).build();
    }
        
}
