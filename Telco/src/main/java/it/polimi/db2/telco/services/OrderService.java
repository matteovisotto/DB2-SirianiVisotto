package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public OrderService(){}

    public Order getOrderById(Integer orderId) throws OrderNotFoundException {
        Order order = em.find(Order.class, orderId);
        if(order == null){
            throw new OrderNotFoundException();
        }
        return order;
    }

    public List<Order> getOrdersOfUser(Integer userId) throws OrderNotFoundException {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.user.id = :user", Order.class);
        query.setParameter("user", userId);
        List<Order> orders = query.getResultList();
        if (orders.size() == 0){
            throw new OrderNotFoundException();
        }
        return orders;
    }

    public Integer createOrder(Order order) {
        em.persist(order);
        em.flush();
        return order.getId();
    }

    public Integer updateOrder(Order order) {
        order = em.merge(order);
        em.flush();
        return order.getId();
    }


    public void deleteOrder(Order order) {
        if (!em.contains(order)) {
            order = em.merge(order);
        }
        em.remove(order);
        em.flush();
    }
}
