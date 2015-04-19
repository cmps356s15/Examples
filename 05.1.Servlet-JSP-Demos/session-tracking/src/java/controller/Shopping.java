package controller;

import entity.Product;
import entity.ProductCategory;
import java.io.*;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import repository.ProductRepository;

@WebServlet("/shop")
public class Shopping extends HttpServlet {

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

        response.setContentType("text/html");
        String[] qty;

        qty = request.getParameterValues("qty");
        for (int i = 0; i < qty.length; i++) {
            response.getWriter().printf("Product %s - Qty %s <br>", i + 1, qty[i]);
        }
    }
}
