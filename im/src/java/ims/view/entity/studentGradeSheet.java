
package ims.view.entity;

import ims.entity.Criteria;

/**
 *
 * @author Mooza Al-nisf
 */
public class studentGradeSheet {
    private Criteria criteria;
    private double grade1;
    private double grade2;
    private String comment;

    
    
    
    
    
    public studentGradeSheet(Criteria criteria, double grade1, double grade2, String comment) {
        this.criteria = criteria;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.comment = comment;
    }

    
    
    
    
    
    
    
    
    
    
    
    
    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public double getGrade1() {
        return grade1;
    }

    public void setGrade1(double grade1) {
        this.grade1 = grade1;
    }

    public double getGrade2() {
        return grade2;
    }

    public void setGrade2(double grade2) {
        this.grade2 = grade2;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
    
    
    
}
