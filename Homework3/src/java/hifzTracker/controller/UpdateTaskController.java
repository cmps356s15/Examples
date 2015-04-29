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
import hifzTracker.repository.SurahRepository;
import hifzTracker.repository.TaskRepository;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/updatetask")
public class UpdateTaskController extends HttpServlet {

    @Inject
    SurahRepository surahRepository;

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
        request.setAttribute("surahList", surahRepository.getSurahs());
        request.setAttribute("task", taskRespository.getTask(user.getId(), taskId));
        
        request.getRequestDispatcher("update-task.jsp").forward(request, response);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Task task = new Task();
            task.setId(Integer.parseInt(request.getParameter("taskId")));    
            task.setFromAya(Integer.parseInt(request.getParameter("fromAya")));
            task.setToAya(Integer.parseInt(request.getParameter("toAya")));
            task.setDueDate(DateUtils.toDate(request.getParameter("dueDate")));
            task.setType(request.getParameter("type"));

            task.setSurah(surahRepository.getSurah(Integer.parseInt(request.getParameter("sura"))));

            User user = (User) request.getSession().getAttribute("user");
            taskRespository.updateTask(user.getId(), task);

            String message = String.format("Task #%d updated successfully!", task.getId());
            request.getSession().setAttribute("message", message);
            response.sendRedirect("pending");
            
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
    //</editor-fold>
}
