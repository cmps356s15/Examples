package hifzTracker.controller;

import hifzTracker.entity.Task;
import hifzTracker.entity.User;
import hifzTracker.repository.TaskRepository;
import hifzTracker.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/completed")
public class CompletedController extends HttpServlet {

    @Inject
    TaskRepository taskRepository;

    @Inject
    UserRepository userRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        List<Task> tasks = taskRepository.getCompletedTasks(user.getId());

        request.setAttribute("completedTaskList", tasks);
        request.setAttribute("user", user);
        request.getRequestDispatcher("completed.jsp").forward(request, response);
    }

}
