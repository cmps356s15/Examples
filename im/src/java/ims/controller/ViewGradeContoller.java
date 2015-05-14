package ims.controller;

import ims.view.entity.studentGradeSheet;
import ims.entity.Criteria;
import ims.entity.Internship;
import ims.entity.Student;
import ims.repository.CriteriaRepository;
import ims.repository.InternshipRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    @Inject
    CriteriaRepository criterias;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Student student = (Student) request.getSession().getAttribute("user");

        Internship internship = internshipRepository.getInternship(student.getStudentId());

        // checking if internship exist and there is a grades objects from both examiners 
        if (student != null && internship != null && internship.getGradings().size() != 0 && internship.getGradings().get(0).getGradingDetails().size() != 0 && internship.getGradings().get(1).getGradingDetails().size() != 0) {

            // get report and presentation criterias from their repositores, and pass them to student-view-grades.jsp
            List<Criteria> allCriterias = criterias.getCriterias();

            // list will be pases to the jsp file to display 
            List<studentGradeSheet> gradesList = new ArrayList<>();

            // variables holds presentation total , report total and the total of each exainer
            double presentationTotal1 = 0;
            double presentationTotal2 = 0;
            double reportTotal1 = 0;
            double reportTotal2 = 0;
            double sumTotal1 = 0;
            double sumTotal2 = 0;

        // get each eaminer's grade for each criteria  
            for (int i = 0; i < allCriterias.size(); i++) {

                int weight1 = internship.getGradings().get(0).getGradingDetails().get(i).getCriteria().getGrade();
                int weight2 = internship.getGradings().get(1).getGradingDetails().get(i).getCriteria().getGrade();

                double percentage1 = internship.getGradings().get(0).getGradingDetails().get(i).getRating().getPercentage();
                double percentage2 = internship.getGradings().get(1).getGradingDetails().get(i).getRating().getPercentage();

                double total1 = weight1 * percentage1;
                double total2 = weight2 * percentage2;

                String comment1 = internship.getGradings().get(0).getGradingDetails().get(i).getComment();
                String comment2 = internship.getGradings().get(1).getGradingDetails().get(i).getComment();

                gradesList.add(new studentGradeSheet(allCriterias.get(i), total1, total2, comment1 + "<br>" + comment2));

             // calculating total of each criteria type 
                if (internship.getGradings().get(0).getGradingDetails().get(i).getCriteria().getCategory().equals("report")) {
                    reportTotal1 += total1;
                    reportTotal2 += total2;
                } else {
                    presentationTotal1 += total1;
                    presentationTotal2 += total2;

                }

                sumTotal1 += total1;
                sumTotal2 += total2;
            }

            // check the letter grade 
            double total = (sumTotal1 + sumTotal2) / 2;
            String letterGrade = "";
            if (total >= 90 && total <= 100) {
                letterGrade = "A";
            } else if (total >= 85 && total < 90) {
                letterGrade = "B+";
            } else if (total >= 80 && total < 85) {
                letterGrade = "B";
            } else if (total >= 75 && total < 80) {
                letterGrade = "C+";
            } else if (total >= 70 && total < 75) {
                letterGrade = "C";
            } else if (total >= 65 && total < 70) {
                letterGrade = "D+";
            } else if (total >= 60 && total < 65) {
                letterGrade = "D";
            } else {
                letterGrade = "F";
            }

        //NOTE: what I'll return is a list of objects that have for each row in the table : criteria, grade1,grade2 and comment from both examiners !! it's easier to display
            request.setAttribute("presentationTotal1", presentationTotal1);
            request.setAttribute("presentationTotal2", presentationTotal2);
            request.setAttribute("reportTotal1", reportTotal1);
            request.setAttribute("reportTotal2", reportTotal2);
            request.setAttribute("total", total);
            request.setAttribute("letterGrade", letterGrade);
            request.setAttribute("examiner1", internship.getGradings().get(0).getExaminer());
            request.setAttribute("examiner2", internship.getGradings().get(1).getExaminer());
            request.setAttribute("gradesList", gradesList);
            request.getRequestDispatcher("student-view-grades.jsp").forward(request, response);

        } else {
            request.getSession().setAttribute("message", String.format("Grade is not available at this moment."));
            response.sendRedirect("studentHome");

        }
    }

}
