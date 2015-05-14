package ims.controller;

import ims.entity.Faculty;
import ims.entity.Internship;
import ims.entity.Student;
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

@WebServlet("/AssignExaminers")
public class AssigExaminersController extends HttpServlet {
    @Inject
    UserRepository userRepository;

    @Inject
    InternshipRepository internships;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int internshipID = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internships.getInternshipById(internshipID);
        List<Faculty> examiners = userRepository.getFaculty();

        request.setAttribute("examinerList", examiners);
        request.setAttribute("internship", internship);
        request.getRequestDispatcher("assign-examiners.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String[] examinersIdString = request.getParameterValues("examiners");

        Faculty examiner;
        int internshipID = Integer.parseInt(request.getParameter("internshipID"));
        Internship internship = internships.getInternshipById(internshipID);

        // read input fields
        String presentationLocation = request.getParameter("pLocation");
        String presentationDate = request.getParameter("pDate");
        String presentationTime = request.getParameter("pTime");

        // add the location , date and time to the internship object
        internship.setPresentationDate(presentationDate);
        internship.setPresentationLocation(presentationLocation);
        internship.setPresentationTime(presentationTime);

        //for every examiner selected
        for (int i = 0; i < examinersIdString.length; i++) {
            //get examiners id as integer
            String id = examinersIdString[i];
            // get examiner object 
            examiner = userRepository.getFaculty(id);
            internship.addExaminer(examiner);
        }
        request.getSession().setAttribute("message", String.format("Successfully assigned examiners to intenship with ID = %d .", internshipID));
        response.sendRedirect("coordinatorHome");
    }
}
