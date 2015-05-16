package jpa.relations.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import jpa.relations.entity.Customer;
import jpa.relations.entity.OrderSummary;

@Stateless
public class CustomerRepository implements ICustomerRepository {

    @PersistenceContext
    private EntityManager em;

    //This is only needed when using JDBC
    @Resource(mappedName = "java:app/jdbc/ContactDB")
    private DataSource dataSource;

    public Customer getCustomerById(long customerId) {
        return em.find(Customer.class, customerId);
    }

    public Customer add(Customer customer) {
        em.persist(customer);
        return customer;
    }

    public Customer addUsingJDBC(Customer customer) throws SQLException {
        String insertQuery = "insert into Customer(customerName) values("
                + customer.getName() + ")";
        try (Connection dbConnection = dataSource.getConnection();
                Statement stmt = dbConnection.createStatement()) {

            int customerId = stmt.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
            customer.setId(customerId);
        }
        return customer;
    }

    public void update(Customer customer) {
        em.merge(customer);
    }

    public int updateUsingJDBC(Customer customer) throws SQLException {
        String updateQuery = "Update Customer set Name = '"
                + customer.getName() + "' where id = "
                + customer.getId();

        System.out.println(updateQuery);
        int numberOfAffectedRows = 0;
        try (Connection dbConnection = dataSource.getConnection();
                Statement stmt = dbConnection.createStatement()) {

            numberOfAffectedRows = stmt.executeUpdate(updateQuery);
        }
        return numberOfAffectedRows;
    }

    public void delete(int id) {
        Customer customer = em.getReference(Customer.class, id);
        em.remove(customer);
    }

    public List<Customer> getCustomers() {
        return em.createQuery("select c from Customer as c").getResultList();
        //return query.getResultList();
    }

    public List<Customer> getCustomersUsingJDBC() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        try (Connection dbConnection = dataSource.getConnection();
                Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM Customer")) {

            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                customers.add(new Customer(id, name));
            }
        }
        return customers;
    }

    public Customer getCustomer(int id) {
        return em.find(Customer.class, id);
    }

    public Customer getCustomer(String customerName) {
        Query query = em.createQuery("select c from Customer c where c.name = :custName");
        query.setParameter("custName", customerName);
        return (Customer) query.getSingleResult();
    }

    public int getCustomersCount() {
        return ((Long) em.createQuery("select count(c) from Customer as c").getSingleResult()).intValue();
    }

    public List<OrderSummary> GetOrdersSummaryUsingJDBC() {

        List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();

        try (Connection con = dataSource.getConnection();
                Statement stmt = con.createStatement()) {

            // Create and execute an SQL statement that returns a
            // set of data and then display it.
            String SQL = "Select C.NAME CustomerName, SUM(O.Qty) TotalQty from CUSTOMER C"
                    + " join ORDERS O on C.ID = O.CUSTOMER_ID"
                    + " Group by C.NAME";

            ResultSet rs = stmt.executeQuery(SQL);

            while (rs.next()) {
                OrderSummary orderSummary = new OrderSummary(
                        rs.getString("CustomerName"),
                        (Long) rs.getLong("TotalQty"));
                orderSummaryList.add(orderSummary);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSummaryList;
    }

    public List<OrderSummary> GetOrdersSummary() {
        Query query = em.createNamedQuery("Customer.GetOrdersSummary");
        return query.getResultList();
    }

    public OrderSummary GetOrdersSummaryByCustomerIdUsingJDBC(int customerId) {
        OrderSummary orderSummary = null;
        String SQL = "Select C.NAME CustomerName, SUM(O.Qty) TotalQty from CUSTOMER C"
                + " join ORDERS O on C.ID = O.CUSTOMER_ID "
                + " Where C.Id = ?"
                + " Group by C.NAME";

        try (Connection con = dataSource.getConnection();
                PreparedStatement stmt = con.prepareStatement(SQL)) {

            // Create and execute an SQL statement that returns a
            // set of data and then display it.
            stmt.setInt(1, customerId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                orderSummary = new OrderSummary(
                        rs.getString("CustomerName"),
                        (Long) rs.getLong("TotalQty"));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderSummary;
    }

    public OrderSummary GetOrdersSummary(int customerId) {
        Query query = em.createNamedQuery("Customer.GetOrdersSummaryByCustomerId");
        query.setParameter("customerId", customerId);
        return (OrderSummary) query.getSingleResult();
    }
}
