package ejb.stateful.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ejb.stateful.model.IShoppingCart;

@WebServlet("/checkout")
public class Checkout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        IShoppingCart shoppingCart = (IShoppingCart) session.getAttribute("ShoppingCart");
        
        if (shoppingCart != null && shoppingCart.getCartItemsCount() > 0) {
            int orderId = shoppingCart.checkout();
            request.setAttribute("OrderId", orderId);
            request.getRequestDispatcher("shopping-confirmation.jsp").forward(request, response);
            session.removeAttribute("ShoppingCart");
        } else {
            request.setAttribute("error", "Your shopping card is empty!");
        }
    }
}
