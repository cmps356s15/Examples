package contact.repository;

import com.google.gson.Gson;
import contact.entity.Contact;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Singleton;

@Singleton
public class ContactRespository {

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

    public void addContact(Contact contact) {
        if (contacts == null) {
            contacts = new ArrayList();
        }
        contacts.add(contact);
        contact.setId(++lastContactId);
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
        return contacts.stream().filter(c -> c.getId() == id).findFirst().get();
    }

    private void insertTestData() {
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

    public List<String> getCountries() {
        List<String> countries = new ArrayList();
        countries.add("Qatar");
        countries.add("Palestine");
        countries.add("Algeria");
        countries.add("Egypt");
        countries.add("Sudan");
        countries.add("Iraq");
        countries.add("Morocco");
        countries.add("Saudi");
        countries.add("Yemen");
        countries.add("Syria");
        countries.add("Tunisia");
        countries.add("Somalia");
        countries.add("UAE");
        countries.add("Libya");
        countries.add("Jordan");
        countries.add("Mauritania");
        countries.add("Oman");
        countries.add("Kuwait");
        countries.add("Bahrain");

        return countries;
    }

    public List<String> getCities(String country) {
        List<String> cities = new ArrayList();
        switch (country.toLowerCase()) {
            case "qatar":
                cities = Arrays.asList("Doha", "Al Khor", "Al Wakrah");
                break;
            case "palestine":
                cities = Arrays.asList("Quds", "Gaza", "Khan Yunis");
                break;
            case "algeria":
                cities = Arrays.asList("Algiers", "Oran", "Constantine");
                break;
            case "egypt":
                cities = Arrays.asList("Cairo", "Alexandria", "Damanhur");
                break;
            case "sudan":
                cities = Arrays.asList("Khartoum", "Wadi Halfa", "Taiyara");
                break;
            case "iraq":
                cities = Arrays.asList("Baghdad", "Basra", "Faluja");
                break;
            case "morocco":
                cities = Arrays.asList("Fes", "Casabalanca", "Rabat");
                break;
            case "saudi":
                cities = Arrays.asList("Mecca", "Madina", "Jeddah");
                break;
            case "yemen":
                cities = Arrays.asList("Sana'a", "Aden", "Taizz");
                break;
            case "syria":
                cities = Arrays.asList("Damascus", "Aleppo", "Daraa");
                break;
            case "tunisia":
                cities = Arrays.asList("Tunis", "Sfax", "Soussa");
                break;
            case "somalia":
                cities = Arrays.asList("Mogadishu", "Merca", "Qandala");
                break;
            case "uae":
                cities = Arrays.asList("Dubai", "Abu Dhabi", "Sharjha");
                break;
            case "libya":
                cities = Arrays.asList("Tripoli", "Benghazi", "Misrata");
                break;
            case "jordan":
                cities = Arrays.asList("Amman", "Irbid", "Al-Aqaba");
                break;
            case "mauritania":
                cities = Arrays.asList("Nouakchott", "Nouadhibou", "Rosso");
                break;
            case "oman":
                cities = Arrays.asList("Muscat", "Nizwa", "Sohar");
                break;
            case "kuwait":
                cities = Arrays.asList("Kuwait city", "Ahmed Al Jaber", "Al Abdaliyah");
                break;
            case "bahrain":
                cities = Arrays.asList("Manama", "Riffa", "Muharraq");
                break;
        }

        return cities;
    }
}
