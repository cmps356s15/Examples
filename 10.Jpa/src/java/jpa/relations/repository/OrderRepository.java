package jpa.relations.repository;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;
import jpa.relations.entity.Order;

@Stateless
public class OrderRepository implements IOrderRepository {

    @PersistenceContext
    private EntityManager em;

    // This is only needed when using JDBC
    @Resource(mappedName = "java:app/jdbc/ContactDB")
    private DataSource dataSource;

    public void add(Order order) {
        em.persist(order);
    }

    public void update(Order order) {
        em.merge(order);
    }

    public void delete(int id) {
        Order order = em.getReference(Order.class, id);
        em.remove(order);
    }

    public List<Order> getOrders() {
        Query query = em.createQuery("select o from Order as o");
        return query.getResultList();
    }

    public Order getOrder(int id) {
        return em.find(Order.class, id);
    }

    public int getOrdersCount() {
        return ((Long) em.createQuery("select count(o) from Order as o")
                .getSingleResult()).intValue();
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        Query query = em.createNamedQuery("Order.findOrdersByCustomerId");
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
}