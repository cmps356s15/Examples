package controller;

import entity.OrderItem;
import entity.Product;
import entity.ProductCategory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import repository.ProductRepository;

@WebServlet("/shop")
public class ShoppingController extends HttpServlet {

    @Inject
    public ProductRepository productRepository;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<ProductCategory> productsCategory = productRepository.getProductCategory();

        String selectedCategory = request.getParameter("category");

        System.out.println("selectedCategory : " + selectedCategory);

        //if selectedCategory is not available then use the first one 
        if (selectedCategory == null) {
            selectedCategory = productsCategory.get(0).getCode();
        }

        List<Product> products = productRepository.getProducts(selectedCategory);

        request.setAttribute("products", products);
        request.setAttribute("productsCategory", productsCategory);
        request.setAttribute("selectedCategory", selectedCategory);

        request.getRequestDispatcher("shopping.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        List<OrderItem> order = new ArrayList<>();

        String[] productIds;
        String[] quantities;

        quantities = request.getParameterValues("qty");
        productIds = request.getParameterValues("productId");
        for (int i = 0; i < productIds.length; i++) {
            System.out.printf("Product %s - Qty %s <br>", productIds[i], quantities[i]);
            if (isInteger(quantities[i])) {
                Product product = productRepository.getProduct(Integer.parseInt(productIds[i]));
                int qty = Integer.parseInt(quantities[i]);
                order.add(new OrderItem(product, qty));
            }
        }
        
        request.setAttribute("order", order);
        request.getRequestDispatcher("confirm.jsp").forward(request, response);
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
        }
        return false;
    }
}
