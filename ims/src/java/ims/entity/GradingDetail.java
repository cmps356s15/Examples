package ims.entity;

public class GradingDetail {
    private Grading grading;
    private Criteria criteria;
    private Rating rating;
    private String comment;

    public GradingDetail() {
    }

    public Grading getGrading() {
        return grading;
    }

    public void setGrading(Grading grading) {
        this.grading = grading;
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
}
