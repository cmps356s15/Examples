package hifzTracker.controller;

import hifzTracker.entity.User;
import hifzTracker.repository.UserRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @Inject
    UserRepository userRepository;
   
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        User user = null;

        try {
            user = userRepository.getUser((String) request.getParameter("username"), (String) request.getParameter("password"));

        } catch (NullPointerException e) {

        }

        if (user == null) {

            request.getSession().setAttribute("message", "Username or Password incorrect! please try again");

            response.sendRedirect("login");
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("pending");
        }

    }

}