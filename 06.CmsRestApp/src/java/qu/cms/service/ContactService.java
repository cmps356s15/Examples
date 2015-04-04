package qu.cms.service;

import com.google.gson.Gson;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import qu.cms.entity.Contact;
import qu.cms.repository.ContactRepository;
import qu.cms.repository.IContactRepository;

@Path("/contacts")
public class ContactService {

    @Inject
    IContactRepository contactRepository;
    
    @GET
    @Path("cities/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCities(@PathParam("country") String countryCode) {
        System.out.println("ContactService.getCities(coutryCode) :" + countryCode);
        try {
            Thread.sleep(400);//simulate a long running action
        } catch (Exception ex) {
            Logger.getLogger(ContactRepository.class.getName()).log(Level.SEVERE, "Thread sleep failed", ex);
        }
        List<String> cities = contactRepository.getCities(countryCode);
        System.out.println("cities count " + cities.size());
        Gson gson = new Gson();
        String json = gson.toJson(cities);
        System.out.println(json);
        return Response.ok(json).build();
    }

    //You can test it using Postman Chrome App - http://www.getpostman.com/
    //Url: http://localhost:8080/cmsapp/api/contacts using Get request
    //you get either XML if accept="application/xml" or json if accept="application/json"
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getContacts() {
        String json = (new Gson()).toJson(contactRepository.getContacts());
        return Response.ok(json).build();
    }

    //You can test it using Postman Chrome App - http://www.getpostman.com/
    //Url: http://localhost:8080/cmsapp/api/contacts/1 using Get request
    //you get either XML if accept="application/xml" or json if accept="application/json"
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getContact(@PathParam("id") int contactId) {
        Contact contact = contactRepository.getContact(contactId);
        if (contact != null) {
            return Response.ok(contact).build();
        } else {
            String msg = String.format("Contact # %d not found", contactId);
            return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
        }
    }

    /*You can test it using Postman Chrome App - http://www.getpostman.com/
     Url: http://localhost:8080/cmsapp/api/contacts using Post request
     You can either post XML by setting the Content-Type="application/xml":
     <?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:contact xmlns:ns2="qu.cms.model"><firstName>Samir</firstName><lastName>Ali</lastName><address><street>15 Fun St</street><city>Doha</city><country>Qatar</country></address></ns2:contact>
     * 
     Or you can post json by setting the Content-Type="application/json"
     {"firstName":"Ali","lastName":"Saleh","address":{"street":"15 Fun St","city":"Doha","country":"Qatar"}}
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addContact(Contact contact) {
        try {
            contact = contactRepository.addContact(contact);
            String location = String.format("/contacts/%s", contact.getId());
            String msg = String.format("contact #%s created successfully", contact.getId());
            return Response.created(new URI(location)).entity(msg).build();
        } catch (Exception ex) {
            String msg = String.format("Adding contact failed because : %s",
                    ex.getCause().getMessage());
            return Response.serverError().entity(msg).build();
        }
    }

    /*You can test it using Postman Chrome App - http://www.getpostman.com/
     Url: http://localhost:8080/cmsapp/api/contacts using Put request
     You can either post XML by setting the Content-Type="application/xml":
     <?xml version="1.0" encoding="UTF-8" standalone="yes"?><ns2:contact xmlns:ns2="qu.cms.model"><contactId>2</contactId><firstName>Mariam</firstName><lastName>Ahmed</lastName><address><addressId>2</addressId><street>11 Fun St</street><city>Kuwait City</city><country>Kuwait</country></address></ns2:contact>
     * 
     Or you can post json by setting the Content-Type="application/json"
     {"contactId":1,"firstName":"Samir","lastName":"Ali","address":{"addressId":1,"street":"15 History St","city":"Fes","country":"Morocco"}
     */
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateContact(Contact contact) {
        try {
            contactRepository.updateContact(contact);
            String msg = String.format("Contact #%s updated sucessfully", contact.getId());
            return Response.ok(msg).build();
        } catch (Exception ex) {
            String msg = String.format("Updating contact failed because : \n%s",
                    ex.getMessage());
            return Response.serverError().entity(msg).build();
        }
    }

    /*You can test it using Postman Chrome App - http://www.getpostman.com/
     Url: http://localhost:8080/cmsapp/api/contacts/1 using Delete request
     */
    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") int contactId) {
        try {
            contactRepository.deleteContact(contactId);
            String msg = String.format("Contact #%s deleted sucessfully", contactId);
            return Response.ok(msg).build();
        } catch (Exception ex) {
            String msg = String.format("Deleting contact failed because : %s",
                    ex.getCause().getMessage());
            return Response.serverError().entity(msg).build();
        }
    }
}
