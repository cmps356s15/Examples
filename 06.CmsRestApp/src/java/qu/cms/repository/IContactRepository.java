package qu.cms.repository;

import java.util.List;
import javax.annotation.PostConstruct;
import qu.cms.entity.Contact;

public interface IContactRepository {

    Contact addContact(Contact contact);

    void deleteContact(int contactId);

    List<String> getCities(String country);

    Contact getContact(int id);

    List<Contact> getContacts();

    int getContactsCount();

    List<String> getCountries();

    @PostConstruct
    void insertTestData();

    void updateContact(Contact contact);
    
}
