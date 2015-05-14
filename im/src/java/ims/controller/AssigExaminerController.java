package ims.controller;

import ims.entity.Faculty;
import ims.entity.Internship;
import ims.repository.InternshipRepository;
import ims.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AssignExaminer")
public class AssigExaminerController extends HttpServlet {
    @Inject
    UserRepository userRepository;

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);
        List<Faculty> examiners = userRepository.getFaculty();

        request.setAttribute("examiners", examiners);
        request.setAttribute("internship", internship);
        request.getRequestDispatcher("assign-examiner.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int examinerId = Integer.parseInt(request.getParameter("examiner"));
        
        System.out.println("internshipId: " + request.getParameter("internshipId"));
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);

        // read input fields
        String presentationLocation = request.getParameter("pLocation");
        String presentationDate = request.getParameter("pDate");
        String presentationTime = request.getParameter("pTime");

        // add the location , date and time to the internship object
        internship.setPresentationDate(presentationDate);
        internship.setPresentationLocation(presentationLocation);
        internship.setPresentationTime(presentationTime);

        Faculty examiner = userRepository.getFaculty(examinerId);
        internship.setExaminer(examiner);
        
        internshipRepository.updateInternship(internship);
        
        request.getSession().setAttribute("message", String.format("Examiners successfully assigned to intenship #%d", internshipId));
        response.sendRedirect("internships");
    }
}
