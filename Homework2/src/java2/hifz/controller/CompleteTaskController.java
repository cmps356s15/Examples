package hifzTracker.controller;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hifzTracker.entity.Task;
import hifzTracker.entity.Task;
import hifzTracker.entity.User;
import hifzTracker.repository.TaskRepository;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/completetask")
public class CompleteTaskController extends HttpServlet {

    @Inject
    TaskRepository taskRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = (Task) taskRespository.getTask(taskId, (User) request.getSession().getAttribute("user"));
        request.getSession().setAttribute("task", task);
        
        //Forward to the jsp page for rendering
        request.getRequestDispatcher("complete-task.jsp").forward(request, response);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //Get the form data and create a Customer Object
            Task task = (Task) request.getSession().getAttribute("task");
            //House keeping, the session is used as the request scope is not enough
            request.getSession().removeAttribute("task");
            
            Task completedTask = new Task();

            //System.err.println(request.getParameter("comment"));
            completedTask.setTaskID(task.getTaskId());
            completedTask.setSurah(task.getSurah());
            completedTask.setfromAya(task.getfromAya());
            completedTask.settoAya(task.gettoAya());
            completedTask.setType(task.getType());

            completedTask.setHifzLevel(request.getParameter("level"));
            completedTask.setCompletedDate(request.getParameter("completedDate"));
            completedTask.setComment(request.getParameter("comment"));

            taskRespository.deleteTask(task.getTaskId(), (User) request.getSession().getAttribute("user"));
            taskRespository.addCompletedTask(completedTask, (User) request.getSession().getAttribute("user"));

            //Store a confirmation message
            request.getSession().setAttribute("message", "Task completed.");
            response.sendRedirect("pending");
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
    //</editor-fold>
}
