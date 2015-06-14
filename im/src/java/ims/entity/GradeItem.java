package ims.entity;

import ims.repository.Utils;

public class GradeItem {
    private Criteria criteria;
    private Rating rating;
    private String comment;
    
    public GradeItem() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    
    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }
    
    public double getSubTotal() {
        return Utils.round(criteria.getGrade() * rating.getPercentage(), 2);
    }
}
