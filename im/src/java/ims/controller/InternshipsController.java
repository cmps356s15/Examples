package ims.controller;

import ims.entity.Faculty;
import ims.entity.Internship;
import ims.repository.InternshipRepository;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/internships")
public class InternshipsController extends HttpServlet {

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Faculty coordinator = (Faculty) request.getSession().getAttribute("user");
        //if this page is redirected after the login , "selectedState" would be null
        if (coordinator == null) {
            response.sendRedirect("login");
            return;
        }
        String selectedState = request.getParameter("state");

        if (selectedState == null) {
            selectedState = "pending";
        }
        List<Internship> internships = internshipRepository.getInternships(selectedState);

        request.setAttribute("internships", internships);
        request.setAttribute("selectedState", selectedState);
        request.setAttribute("states", internshipRepository.getInternshipStates());

        request.getRequestDispatcher("internships.jsp").forward(request, response);
    }
        
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request, response);
    }
}
