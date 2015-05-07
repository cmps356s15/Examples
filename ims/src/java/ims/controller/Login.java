package ims.controller;

import ims.entity.Faculty;
import ims.entity.Student;
import ims.entity.User;
import ims.repository.*;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

    @Inject
    UserRepository userRepository;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            //Get the form data and create a Customer Object
            User user = userRepository.getUser(request.getParameter("username"), request.getParameter("password"));
            
            if (user != null) {
                if (user instanceof Student) {
                    request.getSession().setAttribute("user", (Student) user);
                    response.sendRedirect("registerInternship");

                } else if (user instanceof Faculty) {
                    request.getSession().setAttribute("user", (Faculty) user);
                    if (((Faculty) user).isCoordinator()) {
                        response.sendRedirect("pendingInternships");
                    } else {
                        response.sendRedirect("gradeInternships");
                    }
                }

            } else {
                request.getSession().setAttribute("message", "Invaild username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
}