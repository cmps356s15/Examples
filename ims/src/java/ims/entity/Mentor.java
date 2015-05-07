package ims.entity;

public class Mentor {
    private String firstName;
    private String lastName;
    private String email;
    private String officePhone;
    private String mobile;

    public Mentor(String firstName, String lastName, String email, String officePhone, String mobile) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.officePhone = officePhone;
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
