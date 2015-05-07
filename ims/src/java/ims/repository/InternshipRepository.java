package ims.repository;

import ims.entity.Criteria;
import ims.entity.Internship;
import ims.entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class InternshipRepository {

    @Inject
    CriteriaRepository critereaRepo;
    @Inject
    RatingRepository ratingRepo;

    List<Internship> internships;
    private int lastInternId = 0;

    public List<Internship> getInternships() {
        return internships;
    }

    public List<Internship> getPendingInternships() {
        return internships.stream().filter(c -> c.IsConfirmed() == false).collect(Collectors.toList());
    }

    public List<Internship> getConfirmedInternships() {
        return internships.stream().filter(c -> c.IsConfirmed() == true).collect(Collectors.toList());
    }

    public Internship getInternship(int id) {
        return internships.get(id);
    }

    public int addInternship(Student student) {
        if (internships == null) {
            internships = new ArrayList<>();
        }
        Internship internship = new Internship(student.getStudentId());
        internship.setId(++lastInternId);
        internships.add(internship);
        return internship.getId();
    }

    public Map<String, Double> getGrade(int internshipID) {

        try {
            Internship internship = getInternship(internshipID);
            Map<String, Double> totalGrades = new TreeMap<String, Double>();

            for (String category : critereaRepo.getCategories()) {
                totalGrades.put(category, 0.0);
                totalGrades.put(category + "Total", 0.0);

            }
            totalGrades.put("total", 0.0);

            for (Integer facultyID : internship.getExaminers().keySet()) {
                for (Criteria criterea : critereaRepo.getCriterias()) {
                    double critereaGrade = 0.5 * ((internship.getExaminers().get(facultyID) == null) ? 0.0 : (criterea.getGrade() * ratingRepo.getRating(internship.getExaminers().get(facultyID).getGradeForCriterea(criterea.getId())).getPercentage()));

                    totalGrades.put(criterea.getCategory(), totalGrades.get(criterea.getCategory()) + critereaGrade);
                    totalGrades.put(criterea.getCategory() + "Total", totalGrades.get(criterea.getCategory() + "Total") + 0.5 * (double) criterea.getGrade());
                    totalGrades.put("total", totalGrades.get("total") + critereaGrade);
                }
            }
            return totalGrades;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getLetterGrade(int internshipID) {
        double totalGrade = getGrade(internshipID).get("total");

        if (totalGrade > 90) {
            return "A";
        } else if (totalGrade > 85) {
            return "B+";
        } else if (totalGrade > 80) {
            return "B";
        } else if (totalGrade > 75) {
            return "C+";
        } else if (totalGrade > 70) {
            return "C";
        } else if (totalGrade > 65) {
            return "D+";
        } else if (totalGrade > 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
