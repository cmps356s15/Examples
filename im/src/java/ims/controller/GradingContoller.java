package ims.controller;

import ims.entity.Criteria;
import ims.entity.Faculty;
import ims.entity.GradeItem;
import ims.entity.Internship;
import ims.entity.Rating;
import ims.repository.GradingRepository;
import ims.repository.InternshipRepository;
import ims.repository.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/grading")
public class GradingContoller extends HttpServlet {

    @Inject
    GradingRepository gradingRepository;
    @Inject
    UserRepository userRepository;
    @Inject
    InternshipRepository internshipRepository;

        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login");
            return;
        }

        int selectedInternshipId = 0;
        //if (request.getSession().getAttribute("internshipId") != null) {
        if (request.getParameter("internshipId") != null) {
            selectedInternshipId = Integer.parseInt(request.getParameter("internshipId")); 
            //Integer.parseInt(request.getSession().getAttribute("selectedInternshipId").toString());
        }

        Faculty examiner = (Faculty) request.getSession().getAttribute("user");
        List<Internship> internships = internshipRepository.getInternships(examiner.getStaffNo());

        if (internships != null && !internships.isEmpty()) {
            System.out.println("internships.count: " + internships.size());
            
            Internship selectedInternship = null;
            if (selectedInternshipId == 0) {
                selectedInternship = internships.get(0);
                selectedInternshipId = selectedInternship.getId();
            } else {
                final int id = selectedInternshipId ;
                selectedInternship = internships.stream().filter(i -> i.getId() == id).findFirst().get();
            }
            
            request.setAttribute("selectedInternshipId", selectedInternshipId);
            request.setAttribute("internship", selectedInternship);
            request.setAttribute("internships", internships);
            request.setAttribute("criteria", gradingRepository.getCriteria());
            request.setAttribute("ratings", gradingRepository.getRatings());
        }
        request.getRequestDispatcher("grading.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ratings = request.getParameterValues("rating");
        String[] comments = request.getParameterValues("comment");

        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);
        List<GradeItem> gradeItems = new ArrayList<>();
        List<Criteria> criteriaList = gradingRepository.getCriteria();

        // loop over each criteria + get its rating + its comment
        for (int i = 0; i < criteriaList.size(); i++) {
            GradeItem gradeItem = new GradeItem();
            Criteria criteria = criteriaList.get(i);
            int ratingID = Integer.parseInt(ratings[i]);
            Rating rating = gradingRepository.getRating(ratingID);
            String comment = comments[i];

            gradeItem.setCriteria(criteria);
            gradeItem.setRating(rating);
            gradeItem.setComment(comment);
            gradeItems.add(gradeItem);
        }

        internship.setGradeItems(gradeItems);
        internshipRepository.updateInternship(internship);
        System.out.printf("Grade: %.2f", internship.getTotalGrade());

        request.getSession().setAttribute("message", String.format("Internship grading done for internship #%d", internshipId));
        response.sendRedirect("grading?internshipId=" + internshipId);
    }
}
