package calculator.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calculator.model.ICalculator;

@WebServlet("/calc")
public class CalculatorController extends HttpServlet {

    @Inject
    ICalculator calculator;

    protected void doGet(HttpServletRequest request,
    		HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("index.html").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request,
    		HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter outputToBrowser = response.getWriter();
        try {

            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));

            int result = calculator.add(num1, num2);

            outputToBrowser.println("Result:");
            outputToBrowser.println("" + num1 + " + " + num2 + " = " + result);

        } finally {
            outputToBrowser.close();
        }
    }
}
