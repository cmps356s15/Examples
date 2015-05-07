package ims.controller;

import ims.entity.Internship;
import ims.repository.CompanyRepository;
import ims.repository.CriteriaRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ims.repository.InternshipRepository;
import ims.repository.RatingRepository;
import ims.repository.UserRepository;
import javax.servlet.http.HttpSession;

@WebServlet("/viewGrades")
public class ViewGrades extends HttpServlet {

   @Inject
    InternshipRepository internshipRepo;
    
    @Inject
    CriteriaRepository critereaRepo;
    
    @Inject
    RatingRepository ratingRepo;
    
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
        
        Internship internship = internshipRepo.getInternship(Integer.parseInt(request.getParameter("internshipIndex")));
        
        request.setAttribute("categories", critereaRepo.getCategories());
        request.setAttribute("companies", companyRepo.getCompanies());
        request.setAttribute("critereas", critereaRepo.getCriterias());
        request.setAttribute("ratings", ratingRepo.getRatings());
        request.setAttribute("internship", internship);
        request.setAttribute("staffMembers", userRepo.getFaculty());
        request.setAttribute("internshipGrade", internshipRepo.getGrade(internship.getId()));
        request.setAttribute("internshipLetterGrade", internshipRepo.getLetterGrade(internship.getId()));
        request.getRequestDispatcher("viewGrades.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
