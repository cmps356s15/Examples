package ims.entity;

import java.util.Date;
import java.util.List;

public class Grading {
    private int id;
    private Faculty examiner;
    private Internship internship;
    private Date date;
    private List<GradingDetail> gradingDetails ;

    public Grading() {
    }

    public List<GradingDetail> getGradingDetails() {
        return gradingDetails;
    }

    public void setGradingDetails(List<GradingDetail> gradingDetails) {
        this.gradingDetails = gradingDetails;
    }
     public void addGradingDetails(GradingDetail gradingDetail) {
        this.gradingDetails.add(gradingDetail);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Faculty getExaminer() {
        return examiner;
    }

    public void setExaminer(Faculty examiner) {
        this.examiner = examiner;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
