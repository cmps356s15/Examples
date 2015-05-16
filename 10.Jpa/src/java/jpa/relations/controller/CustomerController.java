package jpa.relations.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jpa.relations.entity.Customer;
import jpa.relations.entity.Order;
import jpa.relations.entity.OrderSummary;
import jpa.relations.repository.ICustomerRepository;
import jpa.relations.repository.IOrderRepository;

@WebServlet("/customer")
public class CustomerController extends HttpServlet {

    @Inject
    ICustomerRepository customerRepository;

    @Inject
    IOrderRepository orderRepository;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        List<String> outputLines = new ArrayList<>();
        int aliCustomerId = 1;

        outputLines.add("<br><a href='employee'>JPA Inheritance Example</a>");
        // 1. Create some test customers
        outputLines.add("<br><b>1. Create two test customers using JPA - this will also create the database</b>\n");

        Customer customer1 = new Customer("Ali Taleh");
        Customer customer2 = new Customer("Moza Ahmed");

        customer1 = customerRepository.add(customer1);
        customer2 = customerRepository.add(customer2);

        aliCustomerId = customer1.getId();
        outputLines.add(String.format("Two customers were created. Their auto-generated Ids are %d & %d\n",
                customer1.getId(), customer2.getId()));

        // 2. Get customers using JDBC
        outputLines.add("<br><b>2. Get customers using JDBC</b>\n");
        try {
            List<Customer> customers = customerRepository.getCustomersUsingJDBC();

            for (Customer customer : customers) {
                outputLines.add(customer.getId() + "   " + customer.getName());
            }

            outputLines.add("Name before update: " + customers.get(0).getName());
            customers.get(0).setName("Ali Saleh");
            outputLines.add("Name after update: " + customers.get(0).getName());

            int numberOfAffectedRows = customerRepository.updateUsingJDBC(customers.get(0));
            outputLines.add("Number of Affected Rows: " + numberOfAffectedRows);

            customers = customerRepository.getCustomersUsingJDBC();

            outputLines.add("After update");
            for (Customer customer : customers) {
                outputLines.add(customer.getId() + "   " + customer.getName());
            }

            //Add using JDBC
            Customer newCustomer = new Customer("Reda Faleh");
            newCustomer = customerRepository.addUsingJDBC(newCustomer);

            outputLines.add(String.format("A customer was created. Its auto-generated Id is %d\n",
                    newCustomer.getId()));

        } catch (Exception e) {
            outputLines.add(e.getMessage());
        }

        // 3. Get customer 'Ali Taleh' using JPA then update the name to 'Ali Saleh'
        outputLines.add("\n<br><b>3. Get customer 'Ali Taleh' using JPA then update the name to 'Ali Saleh'</b>\n");
        try {

            outputLines.add("Customer details before update:");
            Customer customer = customerRepository.getCustomer(aliCustomerId);
            outputLines.add(customer.getId() + "   " + customer.getName());

            // Update the name
            customer.setName("Ali Saleh");

            // Save the change to the database
            customerRepository.update(customer);

            // Read it again
            outputLines.add("\nCustomer details after update:");
            customer = customerRepository.getCustomer(aliCustomerId);
            outputLines.add(customer.getId() + "   " + customer.getName());

        } catch (Exception e) {
            outputLines.add(e.getMessage());
        }

        // 4. Create a new Customer then delete it
        outputLines.add("\n<br><b>4. Create a new Customer then delete it</b>");
        Customer customer3 = new Customer("Shrek Dahak");
        customerRepository.add(customer3);

        int newCustomerId = customer3.getId();

        // Read it again
        outputLines.add("Get newly added customer:");
        Customer customer = customerRepository.getCustomer(newCustomerId);
        outputLines.add(customer.getId() + "   " + customer.getName());

        // Delete it
        outputLines.add(String.format("Delete customer: %s  %s",
                customer.getId(), customer.getName()));
        customerRepository.delete(newCustomerId);

        customer = customerRepository.getCustomer(newCustomerId);

        if (customer == null) {
            outputLines.add(String.format(
                    "Customer with Id %d was successfully deleted",
                    newCustomerId));
        }

        // 5. Insert Orders...
        outputLines.add("\n<br><b>5. Insert Orders...</b>\n");
        outputLines.add("Inserting 2 orders for Customer with Id 1");

        customer = customerRepository.getCustomer(1);

        // Create 2 orders
        Order order1 = new Order();
        order1.setAddress("123 University Rd, Doha, Qatar");
        order1.setQty(10);
        customer.addOrder(order1);

        Order order2 = new Order();
        order2.setAddress("567 1st St. Zaid Rd, Dubai, UAE");
        order2.setQty(20);
        customer.addOrder(order2);

        customerRepository.update(customer);

        outputLines.add("Inserting 2 orders for Customer with Id 2");
        customer = customerRepository.getCustomer(2);

        // Create 2 orders
        Order order3 = new Order();
        order3.setAddress("123 University Rd, Doha, Qatar");
        order3.setQty(200);
        customer.addOrder(order3);

        Order order4 = new Order();
        order4.setAddress("567 1st St. Zaid Rd, Dubai, UAE");
        order4.setQty(100);
        customer.addOrder(order4);

        customerRepository.update(customer);

        outputLines.add("\n<br><b>6. Orders Summary using JDBC:</b>");
        List<OrderSummary> orderSummaryList = customerRepository.GetOrdersSummaryUsingJDBC();
        for (OrderSummary orderSummary : orderSummaryList) {
            outputLines.add(orderSummary.geCustomertName() + " \t "
                    + orderSummary.getTotalQty());
        }

        outputLines.add("\n<br><b>7. Orders Summary using JPA:</b>");
        orderSummaryList = customerRepository.GetOrdersSummary();
        for (OrderSummary orderSummary : orderSummaryList) {
            outputLines.add(orderSummary.geCustomertName() + " \t "
                    + orderSummary.getTotalQty());
        }

        outputLines.add("\n<br><b>8. Orders Summary using JDBC for CustomerID 1:</b>");
        OrderSummary orderSummary = customerRepository
                .GetOrdersSummaryByCustomerIdUsingJDBC(1);
        if (orderSummary != null) {
            outputLines.add(orderSummary.geCustomertName() + " \t Qty: "
                    + orderSummary.getTotalQty());
        } else {
            outputLines.add("No order found.");
        }

        outputLines.add("\n<br><b>9. Orders Summary using JPA for CustomerID 1:</b>");
        orderSummary = customerRepository.GetOrdersSummary(1);
        outputLines.add(orderSummary.geCustomertName() + " \t Qty: "
                + orderSummary.getTotalQty());

        outputLines.add("\n<br><b>10. Delete Order with Id 1.</b>");
        orderRepository.delete(1);

        outputLines.add("\n<br><b>11. Orders for CustomerID 1 using JPA (note that only one order left):</b>");
        List<Order> orders = orderRepository.getOrdersByCustomerId(1);
        for (Order order : orders) {
            outputLines.add("OrderId#" + order.getId() + "\t - Ship to: '"
                    + order.getAddress() + "' \t Qty:  " + order.getQty());
        }

        displayOutput(response, outputLines);
    }

    private void displayOutput(HttpServletResponse response,
            List<String> outputLines) {
        try {
            response.setContentType("text/html");
            PrintWriter outToBrowser = response.getWriter();
            for (String line : outputLines) {
                outToBrowser.println(line);
                outToBrowser.println("<br>");
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
