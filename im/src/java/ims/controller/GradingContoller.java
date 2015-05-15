package ims.controller;

import ims.entity.Criteria;
import ims.entity.Faculty;
import ims.entity.Grading;
import ims.entity.Internship;
import ims.entity.Rating;
import ims.repository.CriteriaRepository;
import ims.repository.InternshipRepository;
import ims.repository.RatingRepository;
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
    CriteriaRepository criteriaRepository;
    @Inject
    RatingRepository ratingRepository;
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
            request.setAttribute("criteria", criteriaRepository.getCriteria());
            request.setAttribute("ratings", ratingRepository.getRatings());
        }
        request.getRequestDispatcher("grading.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] ratings = request.getParameterValues("rating");
        String[] comments = request.getParameterValues("comment");

        int internshipId = Integer.parseInt(request.getParameter("internshipId"));
        Internship internship = internshipRepository.getInternshipById(internshipId);
        List<Grading> gradings = new ArrayList<>();
        List<Criteria> criteriaList = criteriaRepository.getCriteria();

        // loop over each criteria + get its rating + its comment
        for (int i = 0; i < criteriaList.size(); i++) {
            Grading grading = new Grading();
            Criteria criteria = criteriaList.get(i);
            int ratingID = Integer.parseInt(ratings[i]);
            Rating rating = ratingRepository.getRating(ratingID);
            String comment = comments[i];

            grading.setCriteria(criteria);
            grading.setRating(rating);
            grading.setComment(comment);
            gradings.add(grading);
        }

        internship.setGradings(gradings);
        internshipRepository.updateInternship(internship);
        System.out.printf("Grade: %.2f", internship.getTotalGrade());

        request.getSession().setAttribute("message", String.format("Internship grading done for internship #%d", internshipId));
        //request.getSession().setAttribute("selectedInternshipId", internshipId);

        response.sendRedirect("grading?internshipId=" + internshipId);
    }


}
