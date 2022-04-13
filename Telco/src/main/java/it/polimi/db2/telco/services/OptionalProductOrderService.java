/*
package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProductOrder;
import it.polimi.db2.telco.exceptions.optionalProductOrder.OptionalProductOrderNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Transactional
public class OptionalProductOrderService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public OptionalProductOrderService(){}

    public OptionalProductOrder getOptionalProductOrderByOptionalProductIdAndOrderId(Integer optionalId, Integer orderId) throws OptionalProductOrderNotFoundException {
        TypedQuery<OptionalProductOrder> query = em.createQuery("SELECT o FROM OptionalProductOrder o WHERE o.optional.id = :optionalId AND o.order.id = :orderId", OptionalProductOrder.class);
        query.setParameter("optionalId", optionalId);
        query.setParameter("orderId", orderId);
        OptionalProductOrder optionalProductOrder = query.getSingleResult();
        if(optionalProductOrder == null){
            throw new OptionalProductOrderNotFoundException();
        }
        return optionalProductOrder;
    }

    public OptionalProductOrder getOptionalProductOrderByOrderId(Integer orderId) throws OptionalProductOrderNotFoundException {
        TypedQuery<OptionalProductOrder> query = em.createQuery("SELECT o FROM OptionalProductOrder o WHERE o.order.id = :orderId", OptionalProductOrder.class);
        query.setParameter("orderId", orderId);
        OptionalProductOrder optionalProductOrder = query.getSingleResult();
        if(optionalProductOrder == null){
            throw new OptionalProductOrderNotFoundException();
        }
        return optionalProductOrder;
    }

    public OptionalProductOrder getOptionalProductOrderByOptionalProductId(Integer optionalId) throws OptionalProductOrderNotFoundException {
        TypedQuery<OptionalProductOrder> query = em.createQuery("SELECT o FROM OptionalProductOrder o WHERE o.optional.id = :optionalId", OptionalProductOrder.class);
        query.setParameter("optionalId", optionalId);
        OptionalProductOrder optionalProductOrder = query.getSingleResult();
        if(optionalProductOrder == null){
            throw new OptionalProductOrderNotFoundException();
        }
        return optionalProductOrder;
    }

    public void createOptionalProductOrder(OptionalProductOrder optionalProductOrder) {
        em.persist(optionalProductOrder);
        em.flush();
    }
}
*/
