package ims.repository;

import ims.entity.Criteria;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CriteriaRepository {

    private List<Criteria> criteria;
    private final String criteriaUrl = "http://erradi.github.io/json/criteria.json";

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
}
