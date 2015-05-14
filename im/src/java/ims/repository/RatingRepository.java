package ims.repository;

import ims.entity.Rating;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class RatingRepository {

    private List<Rating> Ratings;
    private final String ratingUrl = "http://erradi.github.io/json/rating.json";

    public List<Rating> getRatings() {
        if (Ratings == null) {
            loadRating();
        }
        return Ratings;
    }

    public Rating getRating(int id) {
        if (Ratings == null) {
            loadRating();
        }
            return Ratings.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void loadRating() {
        if (Ratings != null && Ratings.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String ratingStr = Utils.readUrl(ratingUrl);
        System.out.println(ratingStr);

        Rating[] criteriaArray = gson.fromJson(ratingStr, Rating[].class);

        Ratings = new ArrayList<>(Arrays.asList(criteriaArray));
    }
}
