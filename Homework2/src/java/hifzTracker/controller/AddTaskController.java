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

@WebServlet("/addtask")
public class AddTaskController extends HttpServlet {

    @Inject
    SurahRepository surahRepository;

    @Inject
    TaskRepository taskRepository;

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

        response.setCharacterEncoding("UTF-8");
        request.setAttribute("surahList", surahRepository.getSurahs());

        request.getRequestDispatcher("add-task.jsp").forward(request, response);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            Task task = new Task();
            task.setFromAya(Integer.parseInt(request.getParameter("fromAya")));
            task.setToAya(Integer.parseInt(request.getParameter("toAya")));
            task.setDueDate(DateUtils.toDate(request.getParameter("dueDate")));
            task.setType(request.getParameter("type"));
            task.setSurah(surahRepository.getSurah(Integer.parseInt(request.getParameter("sura"))));

            User user = (User) request.getSession().getAttribute("user");
            taskRepository.addTask(user.getId(), task);

            //Store a confirmation message
            request.getSession().setAttribute("message", "Task created successfully!");
            response.sendRedirect("pending");
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }

    //</editor-fold>
}
