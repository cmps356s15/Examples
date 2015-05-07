package ims.controller;

import ims.entity.Faculty;
import ims.entity.Grade;
import ims.entity.Internship;
import ims.entity.TotalGrade;
import ims.repository.CompanyRepository;
import ims.repository.CriteriaRepository;
import ims.repository.InternshipRepository;
import ims.repository.RatingRepository;
import ims.repository.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/gradeInternships")
public class GradeInternship extends HttpServlet {

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

        int internshipIndex = 0;
        Faculty user = (Faculty) request.getSession().getAttribute("user");
        List<Internship> myInternships = null; //internshipRepo.getInternships(user.getUsername());

        request.setAttribute("internships", myInternships);

        if (request.getParameterMap().containsKey("internshipIndex")) {
            internshipIndex = Integer.parseInt(request.getParameter("internshipIndex"));
            request.setAttribute("internship", internshipRepo.getInternship(internshipIndex));
        } else if (myInternships.size() != 0) {
            request.setAttribute("internship", ((TreeMap<Integer, Internship>) myInternships).firstEntry().getValue());
        }

        request.setAttribute("categories", critereaRepo.getCategories());
        request.setAttribute("critereas", critereaRepo.getCriterias());
        request.setAttribute("ratings", ratingRepo.getRatings());
        request.setAttribute("companies", companyRepo.getCompanies());
        request.getRequestDispatcher("gradeInternships.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int internshipIndex = Integer.parseInt(request.getParameter("internshipIndex"));
        Internship internship = internshipRepo.getInternship(internshipIndex);

        List<Grade> grades = new ArrayList<Grade>();
        for (int i = 0; i < critereaRepo.getCriterias().size(); i++) {
            int critereaID = critereaRepo.getCriterias().get(i).getId();
            int ratingID = Integer.parseInt(request.getParameter("gradeComponent" + critereaID));
            String comment = request.getParameter("gradeComment" + critereaID);
            grades.add(new Grade(critereaID, ratingID, comment));
        }

        TotalGrade totalGrade = new TotalGrade(grades);
        Faculty faculty = (Faculty) request.getSession().getAttribute("user");

        internship.addExaminerGrade(faculty.getStaffNo(), totalGrade);
        response.sendRedirect("gradeInternships");
    }
}
