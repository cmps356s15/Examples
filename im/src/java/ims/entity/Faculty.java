package ims.entity;

import java.util.ArrayList;
import java.util.List;

public class Faculty extends User{
    private int staffNo;
    private int isCoordinator;
    private String department;
    private String officeNumber;
    private List<Integer> internshipsIDs;

    public Faculty(int staffNo, int isCoordinator, String department, String officeNumber, String FirstName, String LastName, String Email, String Username, String Password) {
        super(FirstName, LastName, Email, Username, Password);
        this.staffNo = staffNo;
        this.isCoordinator = isCoordinator;
        this.department = department;
        this.officeNumber = officeNumber;
    }

    public int getStaffNo() {
        return staffNo;
    }

    public boolean isCoordinator() {
        return isCoordinator == 1;
    }

    public String getDepartment() {
        return department;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setStaffNo(int staffNo) {
        this.staffNo = staffNo;
    }

    public void setIsCoordinator(int isCoordinator) {
        this.isCoordinator = isCoordinator;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
    
    public void addInternship(int internshipId){
        if(internshipsIDs == null) internshipsIDs = new ArrayList();
        this.internshipsIDs.add(internshipId);
    }

    public List<Integer> getInternshipsIDs() {
        return internshipsIDs;
    }
    
    public void setInternshipsIDs(List<Integer> internshipsIDs)
    {
        internshipsIDs = internshipsIDs;
    }
}
