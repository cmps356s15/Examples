package jpa.relations.repository;

import java.util.List;
import jpa.relations.entity.Order;

public interface IOrderRepository {

    public abstract void add(Order order);

    public abstract void update(Order order);

    public abstract void delete(int id);

    public abstract List<Order> getOrders();

    public abstract Order getOrder(int id);

    public abstract int getOrdersCount();

    public abstract List<Order> getOrdersByCustomerId(int customerId);

}
