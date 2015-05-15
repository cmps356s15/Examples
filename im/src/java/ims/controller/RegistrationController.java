package ims.controller;

import ims.entity.Company;
import ims.entity.Internship;
import ims.entity.Student;
import ims.repository.CompanyRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegistrationController extends HttpServlet {

    @Inject
    InternshipRepository internshipRepository;
    @Inject
    CompanyRepository companyRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Company> companies = companyRepository.getCompanies();
        request.setAttribute("companies", companies);
        request.getRequestDispatcher("register-internship.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Student student = (Student) request.getSession().getAttribute("user");

        Internship internship = new Internship();
        internship.setStudent(student);
        internship.setStatus("pending");
        internship.setYear(2015);

        String companies[] = request.getParameterValues("companies");

        if (companies != null && companies.length > 0) {
            List<Company> selectedCompanies = new ArrayList<>();
            for (String id : companies) {
                selectedCompanies.add(companyRepository.getCompany(Integer.parseInt(id)));
            }
            internship.setPreferredCompanies(selectedCompanies);
        }
        int id = internshipRepository.addInternship(internship);
        internship.setId(id);

        request.setAttribute("internship", internship);
        request.getRequestDispatcher("view-internship.jsp").forward(request, response);
    }
}
