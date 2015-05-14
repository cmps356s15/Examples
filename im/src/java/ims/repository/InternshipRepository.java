package ims.repository;

import ims.entity.Company;
import ims.entity.Faculty;
import ims.entity.Internship;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class InternshipRepository {

    @Inject
    CompanyRepository companyRepository;

    private List<Internship> internships = internships = new ArrayList<>();
    private int lastInternshipId = 0;

    public Internship getInternship(int studentId) {
        Optional<Internship> internship = internships.stream().filter(i -> i.getStudent().getStudentId() == studentId).findFirst();
        return internship.isPresent() ? internship.get() : null;
    }

    public Internship getInternshipById(int id) {
        Optional<Internship> internship = internships.stream().filter(i -> i.getId() == id).findFirst();
        return internship.isPresent() ? internship.get() : null;
    }

    public List<Internship> getInternships() {
        return internships;
    }

    public int addInternship(Internship internship) {
        internship.setId(++lastInternshipId);
        internships.add(internship);
        return lastInternshipId;
    }

    public void removeInternship(Internship internship) {
        internships.remove(internship);
    }

    public List<Internship> getInternships(String state) {
        //if state is "all" , just return all intenrships
        if (state.equals("all")) {
            return getInternships();
        }

        List<Internship> selectedInternships = new ArrayList<>();
        for (Internship internship : getInternships()) {
            if (internship.getStatus().equals(state)) {
                selectedInternships.add(internship);
            }
        }
        return selectedInternships;
    }

    public List<String> getInternshipStates() {
        List<String> states = new ArrayList<>();
        states.add("pending");
        states.add("confirmed");
        //states.add("rejected");
        states.add("all");
        //states.add("canceled");
        return states;
    }

    public void updateInternship(Internship internship) {
        for (int i = 0; i < internships.size(); i++) {
            if (internships.get(i).getId() == internship.getId()) {
                internships.set(i, internship);
                break;
            }
        }
    }

    public void confirmInternship(int internshipId, int companyId) {
        Company company = companyRepository.getCompany(companyId);
        Internship internship = getInternshipById(internshipId);
        internship.setHostCompany(company);
        internship.setStatus("confirmed");
        updateInternship(internship);
    }
}
