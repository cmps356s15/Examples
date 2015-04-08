package hifzTracker.controller;

//<editor-fold defaultstate="collapsed" desc="imports">
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hifzTracker.entity.Task;
import hifzTracker.entity.User;
import hifzTracker.repository.TaskRepository;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/updatetask")
public class UpdateTaskController extends HttpServlet {

    @Inject
    TaskRepository taskRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        int taskId = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("surahList", taskRespository.getSurahs());
        request.getSession().setAttribute("task", taskRespository.getTask(taskId, (User) request.getSession().getAttribute("user")));

        //Forward to the jsp page for rendering
        request.getRequestDispatcher("update-task.jsp").forward(request, response);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

           
            Task task = (Task) request.getSession().getAttribute("task");
            task.setfromAya(Integer.parseInt(request.getParameter("fromAya")));
            task.settoAya(Integer.parseInt(request.getParameter("toAya")));
            task.setDueDate(request.getParameter("dueDate"));
            task.setType(request.getParameter("type"));

            task.setSurah(taskRespository.getSurah(Integer.parseInt(request.getParameter("sura"))));

            taskRespository.updatePendingTask(task, (User) request.getSession().getAttribute("user"));

            //Store a confirmation message
            request.getSession().setAttribute("message", "Task updated successfully!");
            response.sendRedirect("pending");
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
    //</editor-fold>
}
