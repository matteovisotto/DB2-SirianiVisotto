package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.SuspendedOrder;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class SuspendedOrderService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public SuspendedOrderService(){}

    public List<SuspendedOrder> getAllSuspendedOrders() {
        TypedQuery<SuspendedOrder> query = em.createQuery("SELECT s FROM SuspendedOrder s", SuspendedOrder.class);
        return query.getResultList();
    }
}
