package ims.controller;

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

@WebServlet(name = "pendingInternships", urlPatterns = {"/pendingInternships"})
public class PendingInternships extends HttpServlet {
    @Inject
    InternshipRepository internshipRepo;
    
    @Inject
    UserRepository userRepo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
        }

        request.setAttribute("internships", internshipRepo.getInternships());
        request.getRequestDispatcher("pendingInternships.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }



}
