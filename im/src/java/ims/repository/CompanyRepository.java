package ims.repository;

import ims.entity.Company;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.ejb.Singleton;

@Singleton
public class CompanyRepository {

    private List<Company> companies = new ArrayList<>();
    private int lastCompanyId;
    private final String companiesUrl = "http://erradi.github.io/json/company.json";

    public Company getCompany(int id) {
        if (companies.isEmpty()) {
            this.initialize();
        }

        Optional<Company> company
                = companies.stream().filter(c -> c.getId() == id).findFirst();

        return company.isPresent() ? company.get() : null;
    }

    public Company getCompany(String name) {
        if (companies.isEmpty()) {
            this.initialize();
        }
        Optional<Company> company
                = companies.stream().filter(c -> c.getName().equals(name)).findFirst();

        return company.isPresent() ? company.get() : null;
    }

    public void initialize() {
        if (companies != null && !companies.isEmpty()) {
            return;
        }

        Gson gson = new Gson();
        String companiesStr = Utils.readUrl(companiesUrl);

        Company[] companyArray = gson.fromJson(companiesStr, Company[].class);
        //Convert the array to a editable list 
        companies = new ArrayList<>(Arrays.asList(companyArray));

        lastCompanyId = companies.size() + 2;
    }

    public List<Company> getCompanies() {
        if (companies.isEmpty()) {
            initialize();
        }
        return companies;
    }

    public int addCompany(Company company) {
        company.setId(++lastCompanyId);
        companies.add(company);
        return lastCompanyId;
    }

    public boolean exists(String companyName) {
        return (getCompany(companyName) != null);
    }
}