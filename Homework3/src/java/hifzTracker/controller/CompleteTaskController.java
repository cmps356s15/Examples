package hifzTracker.controller;

//<editor-fold defaultstate="collapsed" desc="imports">
import hifzTracker.common.DateUtils;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hifzTracker.entity.Task;
import hifzTracker.entity.User;
import hifzTracker.repository.TaskRepository;
import java.util.Date;
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

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int taskId = Integer.parseInt(request.getParameter("id"));
        Task task = taskRespository.getTask(user.getId(), taskId);
        request.setAttribute("task", task);

        //Forward to the jsp page for rendering
        request.getRequestDispatcher("complete-task.jsp").forward(request, response);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            String level = request.getParameter("level");
            Date completedDate = DateUtils.toDate(request.getParameter("completedDate"));
            String comment = request.getParameter("comment");

            User user = (User) request.getSession().getAttribute("user");
            taskRespository.completeTask(user.getId(), taskId, completedDate, level, comment);

            //Store a confirmation message
            request.getSession().setAttribute("message", "Task completed.");
            response.sendRedirect("pending");
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
    //</editor-fold>
}
