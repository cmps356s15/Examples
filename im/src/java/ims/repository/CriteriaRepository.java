package ims.repository;

import ims.entity.Criteria;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CriteriaRepository {

    private List<Criteria> criterias;
    private final String criteriaUrl = "http://erradi.github.io/json/criteria.json";

    public List<Criteria> getCriteria() {
        if (criterias == null) {
            loadCriteria();
        }
        return criterias;
    }

    public Criteria getCriteria(int id) {
        if (criterias != null) {
            loadCriteria();
        }
        return criterias.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void loadCriteria() {
        if (criterias != null && criterias.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String criteriasStr = Utils.readUrl(criteriaUrl);
        System.out.println(criteriasStr);

        Criteria[] criteriaArray = gson.fromJson(criteriasStr, Criteria[].class);

        criterias = new ArrayList<>(Arrays.asList(criteriaArray));
    }
}
