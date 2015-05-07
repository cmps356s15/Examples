package ims.controller;

import ims.entity.Company;
import ims.entity.Internship;
import ims.repository.CompanyRepository;
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

@WebServlet("/confirmInternship")
public class ConfirmInternship extends HttpServlet {

    @Inject
    InternshipRepository internshipRepo;
    
    @Inject
    UserRepository userRepo;
    
    @Inject
    CompanyRepository companyRepo;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
        }
        
        int internshipID = Integer.parseInt(request.getParameter("internshipID"));
        Internship internship = internshipRepo.getInternship(internshipID);
        //User user = userRepo.getStudent(internship.getStudentID());
        request.setAttribute("internship", internship);
        //request.setAttribute("user", user);
        request.setAttribute("companies", companyRepo.getCompanies());
        request.getRequestDispatcher("confirmInternship.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String selectFrom = request.getParameter("selectFrom");
        int selectedCompanyID;
        int internshipIndex = Integer.parseInt(request.getParameter("internshipIndex"));
        if (selectFrom.equals("existing"))
        {
          System.out.println("existing");
          selectedCompanyID = Integer.parseInt(request.getParameter("selectedCompany"));

        }
        else
        {
            String companyName = request.getParameter("companyName");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String website = request.getParameter("website");
            
            selectedCompanyID = companyRepo.getCompanies().size()+5;
            companyRepo.addCompany(new Company(selectedCompanyID,companyName,street,city,phone,website,email));
            
        }
       
            internshipRepo.getInternship(internshipIndex).setIsConfirmed(true);
            internshipRepo.getInternship(internshipIndex).setCompanieID(selectedCompanyID);
            
            response.sendRedirect("pendingInternships");
    }
}
