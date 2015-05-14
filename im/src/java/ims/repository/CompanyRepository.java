package ims.repository;

import ims.entity.Company;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CompanyRepository {

    private List<Company> companies = new ArrayList<>();
    private int lastId = 26;
    private final String companiesUrl = "http://erradi.github.io/json/company.json";

    public Company getCompany(int id) {
        if (companies.size() == 0) {
            this.initialize();
        }

        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getId() == id) {
                return companies.get(i);
            }
        }

        return null;

    }

    public Company getCompany(String name) {
        if (companies.size() == 0) {
            this.initialize();
        }

        for (int i = 0; i < companies.size(); i++) {
            if (companies.get(i).getName().equals(name)) {
                return companies.get(i);
            }
        }

        return null;
    }

    public void initialize() {
        if (companies != null && companies.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String companiesStr = Utils.readUrl(companiesUrl);

        Company[] companyArray = gson.fromJson(companiesStr, Company[].class);
        //Convert the array to a editable list 
        companies = new ArrayList<>(Arrays.asList(companyArray));
    }

    public List<Company> getCompanies() {
        if (companies.size() == 0) {
            initialize();
        }
        return companies;
    }

    public int addCompany(Company company) {
        company.setId(++lastId);
        companies.add(company);
        return lastId;
    }

    public boolean exists(String companyName) {
        return (getCompany(companyName) != null);
    }
}
