package ims.controller;

import ims.entity.Internship;
import ims.entity.Student;
import ims.repository.InternshipRepository;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewGrade")
public class ViewGradeContoller extends HttpServlet {

    @Inject
    InternshipRepository internshipRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }
        Student student = (Student) request.getSession().getAttribute("user");
        Internship internship = internshipRepository.getInternship(student.getStudentId());
        request.setAttribute("internship", internship);
        request.getRequestDispatcher("view-grade.jsp").forward(request, response);
    }
}
