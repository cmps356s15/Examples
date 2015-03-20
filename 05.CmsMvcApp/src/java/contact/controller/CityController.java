package contact.controller;

//<editor-fold defaultstate="collapsed" desc="imports">
import com.google.gson.Gson;
import contact.repository.ContactRespository;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.inject.Inject;
//</editor-fold>

@WebServlet("/cities")
public class CityController extends HttpServlet {

    @Inject
    ContactRespository contactRespository;

    //<editor-fold defaultstate="collapsed" desc="doGet">
    @Override
    protected void doGet(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {

        List<String> cities = contactRespository.getCities(request.getParameter("country"));

        Gson gson = new Gson();
        response.getWriter().println(gson.toJson(cities));
    }

    //</editor-fold>
}
