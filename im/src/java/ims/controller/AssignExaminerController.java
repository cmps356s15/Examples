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
public class AssignExaminerController extends HttpServlet {
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
        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);

        //Assign the location , date and time to the internship
        internship.setPresentationLocation(request.getParameter("presentationLocation"));
        internship.setPresentationDate(request.getParameter("presentationDate"));
        internship.setPresentationTime(request.getParameter("presentationTime"));

        Faculty examiner = userRepository.getFaculty(examinerId);
        internship.setExaminer(examiner);
        
        internshipRepository.updateInternship(internship);
        
        request.getSession().setAttribute("message", String.format("Examiners successfully assigned to intenship #%d", internshipId));
        response.sendRedirect("internships");
    }
}
