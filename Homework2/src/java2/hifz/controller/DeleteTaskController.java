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
        int id = Integer.parseInt(request.getParameter("id"));

        taskRepository.deleteTask(id, (User) request.getSession().getAttribute("user"));
        response.sendRedirect("pending");
    }
}
