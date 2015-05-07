package ims.entity;

import ims.repository.CriteriaRepository;
import ims.repository.RatingRepository;
import javax.inject.Inject;

public class Grade {

    @Inject
    CriteriaRepository criteriaRepository;
    @Inject
    RatingRepository ratingRepository;

    private int critereaID, ratingID;
    private String comment;

    public int getCritereaID() {
        return critereaID;
    }

    public void setCritereaID(int critereaID) {
        this.critereaID = critereaID;
    }

    public int getRatingID() {
        return ratingID;
    }

    public void setRatingID(int ratingID) {
        this.ratingID = ratingID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Grade(int critereaID, int ratingID, String comment) {
        this.critereaID = critereaID;
        this.ratingID = ratingID;
        this.comment = comment;
    }

    public double getGrade()
    {
        return criteriaRepository.getCriteria(this.getCritereaID()).getGrade() * ratingRepository.getRating(this.getRatingID()).getPercentage();
    }
}
