package ims.controller;

import ims.entity.Student;
import ims.repository.CompanyRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/registerInternship")
public class RegisterInternship extends HttpServlet {

    @Inject
    InternshipRepository internshipRepo;
    @Inject
    CompanyRepository companyRepo;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
        }
        request.setAttribute("companies", companyRepo.getCompanies());
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        internshipRepo.addInternship((Student)request.getSession().getAttribute("user"));
        response.sendRedirect("registerInternship");
    }



}
