
package ims.view.entity;

import ims.entity.Criteria;
import ims.entity.Rating;

/**
 *
 * @author Mooza Al-nisf
 */
public class examinerViewSheet {
    private Criteria criteria;
    private Rating rating;
    private String comment;

    
    
    
    
    
    public examinerViewSheet(Criteria criteria, Rating rating, String comment) {
        this.criteria = criteria;
        this.rating = rating;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
    
}
