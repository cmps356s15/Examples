package controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

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
