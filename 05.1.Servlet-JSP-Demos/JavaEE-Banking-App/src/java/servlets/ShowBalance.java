package servlets;

import model.BankCustomerLookup;
import model.BankCustomer;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/** Servlet that reads a customer ID and displays
 *  information on the account balance of the customer
 *  who has that ID.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate/JPA, and Java programming</a>.
 */

@WebServlet(urlPatterns = "/getbalance")
public class ShowBalance extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
      
    BankCustomer currentCustomer =
    BankCustomerLookup.getCustomer(request.getParameter("id"));
    request.setAttribute("customer", currentCustomer);
    String address;
    
    if (currentCustomer == null) {
      address = "UnknownCustomer.jsp";
    } else if (currentCustomer.getBalance() < 0) {
      address = "NegativeBalance.jsp";
    } else if (currentCustomer.getBalance() < 10000) {
      address = "NormalBalance.jsp";
    } else {
      address = "HighBalance.jsp";
    }
    
    request.getRequestDispatcher(address).forward(request, response);
  }
}
