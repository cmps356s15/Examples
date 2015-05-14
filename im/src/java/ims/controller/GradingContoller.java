package ims.controller;

import ims.view.entity.examinerViewSheet;
import ims.entity.Criteria;
import ims.entity.Faculty;
import ims.entity.Grading;
import ims.entity.GradingDetail;
import ims.entity.Internship;
import ims.entity.Rating;
import ims.entity.Student;
import ims.repository.CriteriaRepository;
import ims.repository.GradingRepository;
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
    CriteriaRepository criterias;
    @Inject
    RatingRepository ratings;
    @Inject
    UserRepository users;
    @Inject
    InternshipRepository internships;
    @Inject
    GradingRepository gradingsRepository;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // check if the hidden field is setted or not ( i.e.. he selected an internship)
        if (!"".equals(request.getParameter("students"))) {
            // read student info
            int internshipID = Integer.parseInt(request.getParameter("students"));
            Internship internship = internships.getInternshipById(internshipID);
            
            // read examiner info
            Faculty examiner = (Faculty) request.getSession().getAttribute("user");

            List<Grading> gradings = internship.getGradings();

            String[] ratingsSelected = request.getParameterValues("rating");
            String[] commentEntered = request.getParameterValues("comment");



            Student student = internship.getStudent();

            Grading grading = new Grading();

//            // find the grading for this internship
//            for (int i = 0; i < gradings.size(); i++) {
//
//                if (gradings.get(i).getInternship().getInterId() == internship.getInterId()) {
//                    grading = gradings.get(i);
//
//                    // remove it from examiner list so it wont be duplicated
//                    examiner.getGradings().remove(grading);
//                    break;
//
//                }
//
//            }

            // check if it already exist in the internship 
            for (int i = 0; i < internship.getGradings().size(); i++) {

                if (internship.getGradings().get(i).getExaminer().getStaffNo() == examiner.getStaffNo()) {
                    internship.getGradings().remove(i);

                    break;
                }

            }

            // check if a grading detail object exit, remove it
            if (grading.getGradingDetails().size() > 0) {
                grading.setGradingDetails(new ArrayList<GradingDetail>());
            }

            // get list of criterias 
            List<Criteria> criteriaList = criterias.getCriterias();

            // loop over each criteria + get its rating + its comment
            for (int i = 0; i < criteriaList.size(); i++) {

                Criteria criteria = criteriaList.get(i);

                // get rating id from the jsp file
                int ratingID = Integer.parseInt(ratingsSelected[i]);

                // get rating object
                Rating rating = ratings.getRating(ratingID);

                // get comment
                String comment = commentEntered[i];

                //create grading detail object
                GradingDetail detail1 = new GradingDetail();
                detail1.setComment(comment);
                detail1.setCriteria(criteria);
                detail1.setRating(rating);
                detail1.setGrading(grading);
                grading.addGradingDetails(detail1);

            }

            // add the new grading object 
            internship.addGrading(grading);
            internship.setStudent(student); // reset the studentd to the mofified internship 
            internships.removeInternship(internship); // remove it so it wont be duplicated
            internships.addInternship(internship); // add the new modified internhsip with the same id
            internship.addGrading(grading);
            gradingsRepository.addGrade(grading); // add the grade to the repository

            // show feedback message
            request.getSession().setAttribute("message", String.format("Grade with Internship ID %d was edited.", internshipID));

            // display emty form again 
            response.sendRedirect("ExaminerGradeSheet");

        } else { // he didn't select a student

            // show feedback message
            request.getSession().setAttribute("message", "Select one internship to grade");

            // display emty form again 
            response.sendRedirect("ExaminerGradeSheet");

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {

            // get user object and cast it to Faculty 
            Faculty user = (Faculty) request.getSession().getAttribute("user");

            // if he selected an internship
            if (!"".equals(request.getParameter("interships")) && request.getParameter("interships") != null) {

                // get id from URL
                int internshipID = Integer.parseInt(request.getParameter("interships"));

                // attach it to set a hidden field to use it later in the post method
                request.setAttribute("internshipID", internshipID);

                // check if there are old values, attach them 
                Internship selectedInternship = internships.getInternshipById(internshipID);
                List<Grading> gradings = selectedInternship.getGradings();
                List<examinerViewSheet> contentlist = new ArrayList<>(); // used to hold criteria, rating and comment for every row in the table

                for (int i = 0; i < gradings.size(); i++) {

                    // get this examiner grading object
                    if (gradings.get(i).getExaminer().getStaffNo() == user.getStaffNo()) {

                        // check that there are data entered before
                        if (gradings.get(i).getGradingDetails().size() > 0) {

                            for (int j = 0; j < gradings.get(i).getGradingDetails().size(); j++) {

                                Criteria criteria = gradings.get(i).getGradingDetails().get(j).getCriteria();
                                Rating rating = gradings.get(i).getGradingDetails().get(j).getRating();
                                String comment = gradings.get(i).getGradingDetails().get(j).getComment();
                                examinerViewSheet row = new examinerViewSheet(criteria, rating, comment);
                                contentlist.add(row);

                            }

                            request.setAttribute("contentlist", contentlist);

                            break;

                        }
                    }

                }

            }

            // get list of internships assigned for this examiner and add them to the select list 
            List<Grading> gradings;

//            if (user != null && user.getGradings() != null) // so it doesn't crash ! 
//            {
//                gradings = user.getGradings();
//            } else {
//                gradings = new ArrayList<Grading>(); // create an empty list 
//            }

            List<Internship> interships = new ArrayList<Internship>(); // to hold interships for each grading 

            // loop over the gratings and add the internship inside the list 
//            for (int i = 0; i < gradings.size(); i++) {
//
//                interships.add(gradings.get(i).getInternship());
//
//            }

            // get list of criterias and fill the table 
            List<Criteria> allCriterias = criterias.getCriterias();
            List<Criteria> reportCriterias = new ArrayList<Criteria>();
            List<Criteria> presentationCriterias = new ArrayList<Criteria>();

            // get the ratings from the repository
            List<Rating> ratingList = ratings.getRatings();

            request.setAttribute("interships", interships);
            request.setAttribute("ratingList", ratingList);
            request.setAttribute("Criterias", allCriterias);

            request.getRequestDispatcher("examiner-grade-sheet.jsp").forward(request, response);

        } else {
            response.sendRedirect("login");
        }
    }
}
