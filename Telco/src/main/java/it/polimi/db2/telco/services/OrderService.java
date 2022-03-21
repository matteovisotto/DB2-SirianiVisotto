package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProductOrder;
import it.polimi.db2.telco.entities.OptionalProductOrderId;
import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
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

    public Order getMyOrderByOrderId(Integer orderId, Integer userId) throws OrderNotFoundException {
        Order order = em.find(Order.class, orderId);
        if(order == null || !order.getUser().getId().equals(userId)){
            throw new OrderNotFoundException();
        }
        return order;
    }

    public List<Order> getOrdersOfUser(Integer userId) {
        TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o WHERE o.user.id = :user", Order.class);
        query.setParameter("user", userId);
        List<Order> orders = query.getResultList();
        return orders;
    }

    public Integer createOrder(Order order) {
        try {
            order.setId(null);
            em.persist(order);
            em.flush();
            return order.getId();
        } catch(Exception ignored){
              return null;
        }
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
