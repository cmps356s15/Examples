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

@WebServlet("/deletecontact")
public class DeleteContactController extends HttpServlet {

    @Inject
    ContactRespository contactRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {

        int contactId = Integer.parseInt(request.getParameter("id"));
        contactRespository.deleteContact(contactId);
        
        //Store a confirmation message
        request.getSession().setAttribute("message", String.format("Contact with Id %s was deleted.", contactId));
        response.sendRedirect("contacts");
    }

    //</editor-fold>
}
