package jpa.relations.repository;

import java.sql.SQLException;
import java.util.List;
import jpa.relations.entity.Customer;
import jpa.relations.entity.OrderSummary;

public interface ICustomerRepository {

    public abstract Customer getCustomerById(long customerId);

    public abstract Customer add(Customer customer);

    public abstract Customer addUsingJDBC(Customer customer)
            throws SQLException;

    public abstract void update(Customer customer);

    public abstract int updateUsingJDBC(Customer customer) throws SQLException;

    public abstract void delete(int id);

    public abstract List<Customer> getCustomers();

    public abstract List<Customer> getCustomersUsingJDBC() throws SQLException;

    public abstract Customer getCustomer(int id);

    public abstract Customer getCustomer(String customerName);

    public abstract int getCustomersCount();

    public abstract List<OrderSummary> GetOrdersSummaryUsingJDBC();

    public abstract List<OrderSummary> GetOrdersSummary();

    public abstract OrderSummary GetOrdersSummaryByCustomerIdUsingJDBC(
            int customerId);

    public abstract OrderSummary GetOrdersSummary(int customerId);

}
