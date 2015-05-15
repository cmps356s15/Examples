package ims.controller;

import ims.entity.Faculty;
import ims.entity.Internship;
import ims.entity.Student;
import ims.entity.User;
import ims.repository.InternshipRepository;
import ims.repository.UserRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Inject
    UserRepository userRepository;

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("userRepository" + userRepository);
        User user = userRepository.getUser(username, password);
        if (user == null) {
            request.getSession().setAttribute("message", "Invaild username or password");
            response.sendRedirect("login");
            return;
        }

        request.getSession().setAttribute("user", user);
        if (user instanceof Student) {
            int studentId = ((Student) user).getStudentId();
            Internship internship = internshipRepository.getInternship(studentId);
            if (internship == null) {
                response.sendRedirect("register");
            } else {
                request.setAttribute("internship", internship);
                request.getRequestDispatcher("view-internship.jsp").forward(request, response);
            }
        } else if (user instanceof Faculty) {
            Faculty faculty = (Faculty) user;
            if (faculty.isCoordinator()) {
                response.sendRedirect("internships");
            } else {
                response.sendRedirect("grading");
            }
        }
    }
}