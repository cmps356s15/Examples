package contact.controller;

//<editor-fold defaultstate="collapsed" desc="imports">
import contact.repository.ContactRespository;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import contact.entity.Address;
import contact.entity.Contact;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/addcontact")
public class AddContactController extends HttpServlet {

    @Inject
    ContactRespository contactRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {

        
        List<String> countries = contactRespository.getCountries();

        request.setAttribute("countryList", countries);

        //Forward to the jsp page for rendering
        request.getRequestDispatcher("addcontact.jsp").forward(request, response);
    }

    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="doPost">
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //Get the form data and create a Customer Object
            Contact contact = new Contact();
            contact.setFirstName(request.getParameter("firstName"));
            contact.setLastName(request.getParameter("lastName"));
            contact.setPhone(request.getParameter("phone"));
            contact.setMobile(request.getParameter("mobile"));
            contact.setEmail(request.getParameter("email"));
            
            Address address = new Address(request.getParameter("street"),
                    request.getParameter("city"),
                    request.getParameter("country"));

            contact.setAddress(address);
            contactRespository.addContact(contact);

            //Store a confirmation message
            request.getSession().setAttribute("message", String.format("Contact with Id %s was added.", contact.getId()));
            //request.getRequestDispatcher("contacts").forward(request, response);
            response.sendRedirect("contacts");
        } catch (Exception ex) {
            ex.printStackTrace(response.getWriter());
        }
    }
    //</editor-fold>
}
