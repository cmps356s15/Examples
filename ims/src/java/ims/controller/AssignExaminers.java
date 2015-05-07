package ims.controller;

import ims.entity.Faculty;
import ims.entity.Internship;
import ims.entity.Presentation;
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

@WebServlet("/assignExaminers")
public class AssignExaminers extends HttpServlet {

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
        request.setAttribute("company", internship.getHostCompany());
        request.setAttribute("staffMembers", userRepo.getFaculty());
        request.getRequestDispatcher("assignExaminers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Faculty examiner1 = userRepo.getFaculty(request.getParameter("examinerOne"));
        Faculty examiner2 = userRepo.getFaculty(request.getParameter("examinerTwo"));
        Internship internship = internshipRepo.getInternship(Integer.parseInt(request.getParameter("internshipIndex")));
        internship.setExaminers(examiner1, examiner2);
        internship.setPresentationLocation(request.getParameter("location"));
        internship.setPresentationDate(request.getParameter("date"));
        internship.setPresentationTime(request.getParameter("time"));
        
        response.sendRedirect("confirmedInternships");
    }



}
