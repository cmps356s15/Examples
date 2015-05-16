package ejb.stateful.controller;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ejb.stateful.model.IShoppingCart;

@WebServlet("/shop")
public class Shopping extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IShoppingCart shoppingCart = getShoppingCart(request.getSession());
        request.setAttribute("CartItemsCount", shoppingCart.getCartItemsCount());
        request.getRequestDispatcher("shopping.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IShoppingCart shoppingCart = getShoppingCart(request.getSession());
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        shoppingCart.addItem(request.getParameter("product"), quantity);

        request.setAttribute("CartItemsCount", shoppingCart.getCartItemsCount());
        request.setAttribute("CartItems", shoppingCart.getCartItems());
        request.getRequestDispatcher("shopping.jsp").forward(request, response);
    }

    private IShoppingCart getShoppingCart(HttpSession session) {
        IShoppingCart shoppingCart = (IShoppingCart) session.getAttribute("ShoppingCart");
        if (shoppingCart == null) {
            try {
                //Look up the ShoppingCart bean
                shoppingCart = (IShoppingCart) new InitialContext().lookup("java:global/09.Ejb/ShoppingCart");
                session.setAttribute("ShoppingCart", shoppingCart);
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }
        return shoppingCart;
    }
}
