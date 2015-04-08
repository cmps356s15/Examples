package hifzTracker.controller;

import hifzTracker.entity.User;
import hifzTracker.repository.TaskRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletetask")
public class DeleteTaskController extends HttpServlet {

    @Inject
    TaskRepository taskRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }
                
        int id = Integer.parseInt(request.getParameter("id"));
        taskRepository.deleteTask(user.getId(), id);
        response.sendRedirect("pending");
    }
}
