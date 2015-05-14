package controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingCart;

@WebServlet(name = "CheckoutController", urlPatterns = {"/checkout"})
public class CheckoutController extends HttpServlet {

    @Inject
    ShoppingCart shoppingCart;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");
        
        if (shoppingCart != null && shoppingCart.getCartItemsCount() > 0) {
            int orderId = shoppingCart.checkout();
            request.setAttribute("OrderId", orderId);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
            session.removeAttribute("ShoppingCart");
        } else {
            response.getWriter().write("Your shopping card is empty!");
        }
    }
}
