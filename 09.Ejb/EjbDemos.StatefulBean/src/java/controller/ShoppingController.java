package controller;

import java.io.IOException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ShoppingCart;

@WebServlet("/shop")
public class ShoppingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ShoppingCart shoppingCart = getShoppingCart(request.getSession());
        request.setAttribute("CartItemsCount", shoppingCart.getCartItemsCount());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ShoppingCart shoppingCart = getShoppingCart(request.getSession());
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        shoppingCart.addItem(request.getParameter("ProductName"), quantity);

        request.setAttribute("CartItemsCount", shoppingCart.getCartItemsCount());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private ShoppingCart getShoppingCart(HttpSession session) {
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("ShoppingCart");
        if (shoppingCart == null) {
            try {
                //Look up the bean
                shoppingCart = (ShoppingCart) new InitialContext().lookup("java:global/EjbDemos.StatefulBean/ShoppingCartBean!model.ShoppingCart");
                session.setAttribute("ShoppingCart", shoppingCart);
            } catch (NamingException e) {
                throw new RuntimeException(e);
            }
        }

        return shoppingCart;
    }
}
