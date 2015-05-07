package ims.controller;

import ims.entity.Internship;
import ims.entity.Location;
import ims.entity.Mentor;
import ims.entity.Student;
import ims.repository.InternshipRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateInternship")
public class UpdateInternship extends HttpServlet {

    @Inject
    InternshipRepository internshipRepo;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student user = (Student) request.getSession().getAttribute("user");
        Internship internship = internshipRepo.getInternship(user.getStudentId());

        String firstName, lastName, email, mobile, officePhone, streetName, city, longitude, latitude;
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");
        mobile = request.getParameter("mobile");
        officePhone = request.getParameter("officePhone");
        streetName = request.getParameter("streetName");
        city = request.getParameter("city");
        longitude = request.getParameter("longitude");
        latitude = request.getParameter("latitude");
        internship.setAbstract(request.getParameter("abstract"));
        internship.setMentor(new Mentor(firstName, lastName, email, officePhone, mobile));
        internship.setLoc(new Location(streetName, city, longitude, latitude));
        response.sendRedirect("registerInternship");
    }
}