package ims.controller;

import ims.entity.Company;
import ims.entity.Internship;
import ims.entity.Mentor;
import ims.entity.Student;
import ims.repository.CompanyRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateInternship")
public class UpdateInternshipController extends HttpServlet {

    @Inject
    InternshipRepository internshipRepository;
    @Inject
    CompanyRepository companyRepository;
    Company company;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get student object
        Student student = (Student) request.getSession().getAttribute("user");

        // get his internship 
        Internship internship = internshipRepository.getInternship(student.getStudentId());

        // check the mentor if exist or not
        if (internship.getMentor() == null) // give him empty form
        {
            request.getRequestDispatcher("update-details.jsp").forward(request, response);
        } else {

            // get the details , put them in objects and send them to the jsp page
            Mentor mentor = internship.getMentor();
            Company hostCompany = internship.getHostCompany();
            String internshipAbstract = internship.getInternshipAbstract();

            request.setAttribute("mentor", mentor);
            request.setAttribute("hostCompany", hostCompany);
            request.setAttribute("internshipAbstract", internshipAbstract);
            request.getRequestDispatcher("update-details.jsp").forward(request, response);

        }

    }

    @Override

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("mentorFirstName");
        String lastName = request.getParameter("mentorLastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String mobile = request.getParameter("mobile");
        Student student = (Student) request.getSession().getAttribute("user");

        //create mentor object
        Mentor mentor = new Mentor(firstName, lastName, email, phone, mobile);
        Internship internship = internshipRepository.getInternship(student.getStudentId());

        //assignign the mentor to the internship.
        internship.setMentor(mentor);
        Company company = internship.getHostCompany();
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String gpsCoordinates = request.getParameter("gpsCoordinates");

        //updating the company attributes (street, city and gpsCoordinates)
        company.setStreet(street);
        company.setCity(city);
        //company.setGpsCoordinates(gpsCoordinates);

        //setting the internship abstract
        String internshipAbstract = request.getParameter("abstract");
        internship.setInternshipAbstract(internshipAbstract);

        // send feedbak message 
        request.getSession().setAttribute("message", String.format("Successfully updated Internship details with InternshipID= %d .", internship.getId()));

        //redirecting to studdnt's homepage
        response.sendRedirect("studentHome");
    }
}
