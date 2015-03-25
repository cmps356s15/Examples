package coreservlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/** Servlet that displays a list of items being ordered.
 *  Accumulates them in an ArrayList with no attempt at
 *  detecting repeated items. Used to demonstrate basic
 *  session tracking. Updated to use generics.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, 
 *  Spring, Hibernate, JPA, and Java</a>.
 */

@WebServlet("/order")
public class PlaceOrder extends HttpServlet {
  @Override
  public void doPost (HttpServletRequest request,
                      HttpServletResponse response)
      throws ServletException, IOException {
      
    HttpSession session = request.getSession();
  
      List<String> orderedItems = (List<String>)session.getAttribute("orderedItems");
      if (orderedItems == null) {
        orderedItems = new ArrayList<String>();
      }
      
      String orderedItem = request.getParameter("orderedItem");
      if ((orderedItem != null) && (!orderedItem.trim().equals(""))) {
           orderedItems.add(orderedItem);
      }

      session.setAttribute("orderedItems", orderedItems);
      
      request.getRequestDispatcher("shoppingcart.jsp").forward(request, response);
    }
}
