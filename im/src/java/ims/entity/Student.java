package ims.entity;

public class Student extends User{
    private int studentId;
    private String major;
    private int completedCHs;
    private float gpa;

    public Student(int studentId, String firstName, String lastName, String email, String username, String password, String major, int completedCHs, float gpa) {
        super(firstName, lastName, email, username, password);
        this.studentId = studentId;
        this.major = major;
        this.completedCHs = completedCHs;
        this.gpa = gpa;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getCompletedCHs() {
        return completedCHs;
    }

    public void setCompletedCHs(int completedCHs) {
        this.completedCHs = completedCHs;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
}
