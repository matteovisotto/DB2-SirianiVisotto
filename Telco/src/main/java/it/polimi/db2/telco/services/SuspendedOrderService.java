package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.SuspendedOrder;
import it.polimi.db2.telco.entities.SuspendedOrderId;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SuspendedOrderService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public SuspendedOrderService(){}

    /*public SuspendedOrder getSuspendedOrderByOrderIdAndUserId(Integer orderId, Integer userId) throws SuspendedOrderNotFoundException {
        SuspendedOrderId suspendedOrderId = new SuspendedOrderId();
        suspendedOrderId.setOrderId(orderId);
        suspendedOrderId.setUserId(userId);
        TypedQuery<SuspendedOrder> query = em.createQuery("SELECT s FROM SuspendedOrder s WHERE s.id = :suspendedOrderId", SuspendedOrder.class);
        query.setParameter("suspendedOrderId", suspendedOrderId);
        SuspendedOrder suspendedOrder = query.getSingleResult();
        if(suspendedOrder == null){
            throw new SuspendedOrderNotFoundException();
        }
        return suspendedOrder;
    }

    public List<SuspendedOrder> getMySuspendedOrders(Integer userId) throws SuspendedOrderNotFoundException {
        Query query = em.createNativeQuery("SELECT s FROM SuspendedOrder s WHERE s.userId = ?", SuspendedOrder.class);
        query.setParameter(1, userId);
        List<SuspendedOrder> suspendedOrders = (List<SuspendedOrder>) query.getResultList();
        if (suspendedOrders.size() == 0) {
            throw new SuspendedOrderNotFoundException();
        }
        return suspendedOrders;
    }*/

    public List<SuspendedOrder> getAllSuspendedOrders() {
        TypedQuery<SuspendedOrder> query = em.createQuery("SELECT s FROM SuspendedOrder s", SuspendedOrder.class);
        return query.getResultList();
    }
}
