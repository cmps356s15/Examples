package ims.repository;

import ims.entity.Company;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class CompanyRepository {

    List<Company> companies;
    private final String companyUrl = "http://erradi.github.io/json/company.json";

    public List<Company> getCompanies() {
        if (companies == null) {
            loadCompanies();
        }
        return companies;
    }

    public Company getCompany(int id) {
        return companies.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    public void addCompany(Company company) {
        companies.add(company);
    }
    
    public void loadCompanies() {
        if (companies != null && companies.size() > 0) {
            return;
        }

        Gson gson = new Gson();
        String companiesStr = Utils.readUrl(companyUrl);
        System.out.println(companiesStr);

        Company[] companyArray = gson.fromJson(companiesStr, Company[].class);
        companies = new ArrayList(Arrays.asList(companyArray));
    }
}
