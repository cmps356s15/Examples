package ims.repository;

import ims.entity.Criteria;
import com.google.gson.Gson;
import ims.entity.Rating;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class GradingRepository {

    private List<Criteria> criteria;
    private final String criteriaUrl = "http://erradi.github.io/json/criteria.json";

    private List<Rating> ratings;
    private final String ratingUrl = "http://erradi.github.io/json/rating.json";

    public List<Criteria> getCriteria() {
        if (criteria == null) {
            loadCriteria();
        }
        return criteria;
    }

    public Criteria getCriteria(int id) {
        if (criteria != null) {
            loadCriteria();
        }
        return criteria.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void loadCriteria() {
        if (criteria != null && criteria.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String criteriasStr = Utils.readUrl(criteriaUrl);
        System.out.println(criteriasStr);

        Criteria[] criteriaArray = gson.fromJson(criteriasStr, Criteria[].class);

        criteria = new ArrayList<>(Arrays.asList(criteriaArray));
    }

    public List<Rating> getRatings() {
        if (ratings == null) {
            loadRating();
        }
        return ratings;
    }

    public Rating getRating(int id) {
        if (ratings == null) {
            loadRating();
        }
        return ratings.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void loadRating() {
        if (ratings != null && ratings.size() > 0) {
            return;
        }
        Gson gson = new Gson();
        String ratingStr = Utils.readUrl(ratingUrl);
        System.out.println(ratingStr);

        Rating[] criteriaArray = gson.fromJson(ratingStr, Rating[].class);

        ratings = new ArrayList<>(Arrays.asList(criteriaArray));
    }
}
