package ims.controller;

import ims.entity.Company;
import ims.repository.CompanyRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/company")
public class CompanyController extends HttpServlet {

    @Inject
    CompanyRepository companyRepository;

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int internshipId = Integer.parseInt(request.getSession().getAttribute("internshipId").toString());
        request.setAttribute("internshipId", internshipId);
        request.getRequestDispatcher("add-company.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String companyName = request.getParameter("name");
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));

        if (companyRepository.exists(companyName)) {
            String message = companyName + " already exists";
            request.getSession().setAttribute("message", message);
            request.setAttribute("internshipId", internshipId);
            request.getRequestDispatcher("add-company.jsp").forward(request, response);
            return;
        }

        Company company = new Company();
        company.setName(companyName);
        company.setEmail(request.getParameter("email"));
        company.setWebsite(request.getParameter("website"));
        company.setPhone(request.getParameter("phone"));
        company.setStreet(request.getParameter("street"));
        company.setCity(request.getParameter("city"));

        int companyId = companyRepository.addCompany(company);

        internshipRepository.confirmInternship(internshipId, companyId);
        String message = String.format("Company %s successfully added", companyName);
        message += String.format("<br>Intenship #%d confirmed", internshipId);

        request.getSession().setAttribute("message", message);
        response.sendRedirect("internships");
    }
}
