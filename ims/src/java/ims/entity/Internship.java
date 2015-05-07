package ims.entity;

import java.util.ArrayList;
import java.util.List;

public class Internship {
    private int id;
    private String status;
    private int year;
    private Student student;
    private List<Company> preferredCompanies;
    private Company hostCompany;
    private Mentor mentor;
    private String presentationLocation;
    private String presentationDate;
    private String presentationTime;
    private String internshipAbstract;
    private List<Faculty> examiners;
    private List<Grading> gradings;
        
    public Internship() {
    }

    public Internship(Student student, List<Company> preferredCompanies, int year, String status) {
        this.student = student;
        this.preferredCompanies = preferredCompanies;
        this.year = year;
        this.status = status;

    }

    public Internship(int id, String status, int year, Company hostCompany, Mentor mentor) {
        this.id = id;
        this.status = status;
        this.year = year;
        this.hostCompany = hostCompany;
        this.mentor = mentor;
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

    public List<Grading> getGradings() {
        return gradings;
    }

    public void setGradings(List<Grading> gradings) {
        this.gradings = gradings;
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

    public String getInternshipAbstract() {
        return internshipAbstract;
    }

    public void setInternshipAbstract(String internshipAbstract) {
        this.internshipAbstract = internshipAbstract;
    }

    public void setCompany(Company company) {
        this.hostCompany = company;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
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

    public Mentor getMentor() {
        return mentor;
    }

    public void addGrading(Grading grading) {
        gradings.add(grading);
    }

    public void setExaminers(Faculty examiner1, Faculty examiner2) {
        examiners = new ArrayList<>();
        examiners.add(examiner1);
        examiners.add(examiner2);
    }
    
    List<Faculty> getExaminers() {
        return examiners;
    }
}
