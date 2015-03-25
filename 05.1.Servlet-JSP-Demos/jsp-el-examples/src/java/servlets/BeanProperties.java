package servlets;

/** Servlet that creates some beans whose properties will
 *  be displayed with the JSP 2.0 expression language.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */


import model.Name;
import model.Company;
import model.Employee;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/bean-properties")
public class BeanProperties extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    Name name = new Name("Marty", "Hall");
    Company company =  new Company("coreservlets.com", "Java EE Training and Consulting");
    Employee employee = new Employee(name, company);
    
    request.setAttribute("employee", employee);
    
    request.getRequestDispatcher("bean-properties.jsp").forward(request, response);
  }
}
