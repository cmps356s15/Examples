package hifzTracker.service;

import com.google.gson.Gson;
import hifzTracker.entity.Task;
import hifzTracker.repository.TaskRepository;
import java.net.URI;
import java.util.List;
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

@Path("/tasks")
@Stateless
public class TaskService {

    @Inject
    TaskRepository taskRepository;

    @GET
    @Path("{userId}/pending")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPendingTasks(@PathParam("userId") int userId) {
        List<Task> pendingTasks = taskRepository.getPendingTasks(userId);
        Gson gson = new Gson();

        String tasksJson = gson.toJson(pendingTasks);
        return Response.ok(tasksJson).build();
    }

    @GET
    @Path("{userId}/{taskId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTask(@PathParam("userId") int userId, @PathParam("taskId") int taskId) {
        Task task = taskRepository.getTask(userId, taskId);
        return Response.ok(task).build();
    }

    @GET
    @Path("{userId}/completed")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedTasks(@PathParam("userId") int userId) {
        List<Task> completedTasks = taskRepository.getCompletedTasks(userId);
        Gson gson = new Gson();
        String tasksJson = gson.toJson(completedTasks);

        return Response.ok(tasksJson).build();
    }

    @PUT
    @Path("/{userId}/complete")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response completeTask(@PathParam("userId") int userId, Task task) {
        taskRepository.completeTask(userId, task.getId(), task.getCompletedDate(), task.getLevel(), task.getComment());
        System.out.println("User Id: " + userId);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{userId}/{taskId}")
    public Response deleteTask(@PathParam("userId") int userId, @PathParam("taskId") int taskId) {
        taskRepository.deleteTask(userId, taskId);
        return Response.ok(String.format("Task #%d deleted", taskId)).build();
    }

    @POST
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addTask(@PathParam("userId") int userId, Task task) {
        try {
            int taskId = taskRepository.addTask(userId, task);
            String location = String.format("/hifz/api/tasks/%s/%s", userId, taskId);
            return Response.created(new URI(location)).build();
        } catch (Exception ex) {
            String msg = String.format("Adding tasks failed because : %s",
                    ex.getCause().getMessage());
            return Response.serverError().entity(msg).build();
        }

    }

    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateTask(@PathParam("userId") int userId, Task task) {
        taskRepository.updateTask(userId, task);
        System.err.println("UserId: " + userId);
        return Response.ok().build();
    }
}
