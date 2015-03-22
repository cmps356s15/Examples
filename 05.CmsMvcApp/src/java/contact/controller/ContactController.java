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
import contact.entity.Contact;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/contacts")
public class ContactController extends HttpServlet {

    @Inject
    ContactRespository contactRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {

        List<Contact> contacts = contactRespository.getContacts();

        
        request.setAttribute("contactList", contacts);

        //Forward to the jsp page for rendering
        request.getRequestDispatcher("contact.jsp").forward(request, response);
    }

    //</editor-fold>
}
