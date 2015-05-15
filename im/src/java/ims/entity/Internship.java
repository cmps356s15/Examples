package ims.entity;

import ims.repository.Utils;
import java.util.ArrayList;
import java.util.List;

public class Internship {

    private int id;
    private String status;
    private int year;
    private Student student;
    private List<Company> preferredCompanies;
    private Company hostCompany;
    private String presentationLocation;
    private String presentationDate;
    private String presentationTime;
    private Faculty examiner;
    private List<GradeItem> gradeItems;

    public Internship() {
    }

    public Internship(Student student, List<Company> preferredCompanies, int year, String status) {
        this.student = student;
        this.preferredCompanies = preferredCompanies;
        this.year = year;
        this.status = status;

    }

    public Internship(int id, String status, int year, Company hostCompany) {
        this.id = id;
        this.status = status;
        this.year = year;
        this.hostCompany = hostCompany;
        preferredCompanies = new ArrayList<>();
    }

    public String getPresentationTime() {
        return presentationTime;
    }

    public void setPresentationTime(String presentationTime) {
        this.presentationTime = presentationTime;
    }

    public String getPresentationLocation() {
        return presentationLocation;
    }

    public void setPresentationLocation(String presentationLocation) {
        this.presentationLocation = presentationLocation;
    }

    public String getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(String presentationDate) {
        this.presentationDate = presentationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<GradeItem> getGradeItems() {
        return gradeItems;
    }

    public void setGradeItems(List<GradeItem> gradeItems) {
        this.gradeItems = gradeItems;
    }

    public Company getHostCompany() {
        return hostCompany;
    }

    public void setHostCompany(Company hostCompany) {
        this.hostCompany = hostCompany;
    }

    public List<Company> getPreferredCompanies() {
        return preferredCompanies;
    }

    public void setPreferredCompanies(List<Company> preferredCompanies) {
        this.preferredCompanies = preferredCompanies;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCompany(Company company) {
        this.hostCompany = company;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public int getYear() {
        return year;
    }

    public Company getCompany() {
        return hostCompany;
    }

    public void setExaminer(Faculty examiner) {
        this.examiner = examiner;
    }

    public Faculty getExaminer() {
        return examiner;
    }

    public boolean IsConfirmed() {
        return status.equalsIgnoreCase("confirmed");
    }

    public double getTotalGrade() {
        double grade = 0;
        for (GradeItem gradeItem : gradeItems) {
            System.out.printf("grade %d percent %s", gradeItem.getCriteria().getGrade(), gradeItem.getRating().getPercentage());
            grade += gradeItem.getCriteria().getGrade() * gradeItem.getRating().getPercentage();
        }
        return Utils.round(grade, 2);
    }
}
