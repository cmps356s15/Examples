package ims.controller;

import ims.entity.Company;
import ims.entity.Internship;
import ims.repository.CompanyRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirm")
public class ConfirmInternshipController extends HttpServlet {

    @Inject
    CompanyRepository companyRepository;

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        int companyId = Integer.parseInt(request.getParameter("company"));

        //If companyId = 0 then allow the user to add a company
        if (companyId == 0) {
          request.getSession().setAttribute("internshipId", internshipId);
          response.sendRedirect("company");  
        }
        else {
            internshipRepository.confirmInternship(internshipId, companyId);
            request.getSession().setAttribute("message", String.format("Intenship #%d confirmed", internshipId));
            response.sendRedirect("internships");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);

        List<Company> companies = companyRepository.getCompanies();
        request.setAttribute("internship", internship);
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("confirm.jsp").forward(request, response);
    }
}
