package qu.cms.repository;

import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Singleton;
import qu.cms.entity.Contact;

@Singleton
public class ContactRepository implements IContactRepository {

    private List<Contact> contacts;
    private int lastContactId = 0;
    private final String contactsUrl = "http://erradi.github.io/contacts.js";
    
    public List<Contact> getContacts() {
        if (contacts != null) {
            return contacts;
        }
        else {
            insertTestData();
            return contacts;
        }
    }

    public Contact addContact(Contact contact) {
        if (contacts == null) {
            contacts = new ArrayList();
        }
        contacts.add(contact);
        contact.setId(++lastContactId);
        return contact;
    }
    
    public void updateContact(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
               contacts.set(i, contact);
               break;
            }
        }
    }

    public void deleteContact(int contactId) {
        contacts.removeIf(c -> c.getId() == contactId);
    }

    public Contact getContact(int id) {
        if (contacts == null) {
            insertTestData();
        }
        try  {
        return contacts.stream().filter(c -> c.getId() == id).findFirst().get();
        } catch (Exception ex) {
            return null;
        }
    }

    public int getContactsCount() {
        return contacts == null ? 0 : contacts.size();
    }
    
    public void insertTestData() {
        if (contacts != null && contacts.size() > 0) {
            return;
        }
        
        Gson gson = new Gson();
        String contactsStr = Utils.readUrl(contactsUrl);
        System.out.println(contactsStr);

        Contact[] contactArray = gson.fromJson(contactsStr, Contact[].class);
        //Convert the array to a editable list 
        contacts = new ArrayList<>(Arrays.asList(contactArray));
        lastContactId = contacts.size();
        
        /*
        addContact(new Contact("Juha", "Dahak", "8888-9999", "7777-888", "juha@test.com", 
                    new Address("5 Test St", "Doha", "Qatar")));
        addContact(new Contact("Abbas", "Ibn Farnas", "6666-9999", "9999-888", "abbas@test.com", 
                    new Address("50 Test St", "Doha", "Qatar")));
        addContact(new Contact("Samir", "Saghir", "7777-9999", "9999-888", "samir@test.com", 
            new Address("22 Aspire St", "Dubai", "UAE")));
        addContact(new Contact("Jarir", "Bookstore", "3333-9999", "2222-888", "jarir@test.com", 
            new Address("33 Salman St", "Jeddah", "Saudi")));
        
        //Gson gson = new Gson();
        //System.out.println(gson.toJson(contacts));
       */
    }
  
    public List<String> getCities(String countryCode) {
        List<String> cities = new ArrayList();
        switch (countryCode.toUpperCase()) {
            case "QA":
                cities = Arrays.asList("Doha", "Al Khor", "Al Wakrah");
                break;
            case "PS":
                cities = Arrays.asList("Quds", "Gaza", "Khan Yunis");
                break;
            case "DZ":
                cities = Arrays.asList("Algiers", "Oran", "Constantine");
                break;
            case "EG":
                cities = Arrays.asList("Cairo", "Alexandria", "Damanhur");
                break;
            case "SD":
                cities = Arrays.asList("Khartoum", "Wadi Halfa", "Taiyara");
                break;
            case "IQ":
                cities = Arrays.asList("Baghdad", "Basra", "Faluja");
                break;
            case "MA":
                cities = Arrays.asList("Fes", "Casabalanca", "Rabat");
                break;
            case "SA":
                cities = Arrays.asList("Mecca", "Madina", "Jeddah");
                break;
            case "YE":
                cities = Arrays.asList("Sana'a", "Aden", "Taizz");
                break;
            case "SY":
                cities = Arrays.asList("Damascus", "Aleppo", "Daraa");
                break;
            case "TN":
                cities = Arrays.asList("Tunis", "Sfax", "Soussa");
                break;
            case "SO":
                cities = Arrays.asList("Mogadishu", "Merca", "Qandala");
                break;
            case "AE":
                cities = Arrays.asList("Dubai", "Abu Dhabi", "Sharjha");
                break;
            case "LY":
                cities = Arrays.asList("Tripoli", "Benghazi", "Misrata");
                break;
            case "JO":
                cities = Arrays.asList("Amman", "Irbid", "Al-Aqaba");
                break;
            case "MR":
                cities = Arrays.asList("Nouakchott", "Nouadhibou", "Rosso");
                break;
            case "OM":
                cities = Arrays.asList("Muscat", "Nizwa", "Sohar");
                break;
            case "KW":
                cities = Arrays.asList("Kuwait city", "Ahmed Al Jaber", "Al Abdaliyah");
                break;
            case "BH":
                cities = Arrays.asList("Manama", "Riffa", "Muharraq");
                break;
        }

        return cities;
    }
}
