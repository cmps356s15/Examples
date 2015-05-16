package ejb.stateless.controller;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejb.stateless.model.ICalculator;

@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    @Inject
    ICalculator calculator;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("calculator.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));

        int sum = calculator.add(num1, num2);

        String result = String.format("%d + %d = %d", num1, num2, sum);
        request.getSession().setAttribute("result", result);

        response.sendRedirect("calculator");
    }
}
