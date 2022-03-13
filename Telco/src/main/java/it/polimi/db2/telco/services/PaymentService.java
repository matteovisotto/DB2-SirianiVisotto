package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PaymentHistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public PaymentService(){}

    public PaymentHistory getPaymentById(Integer paymentHistoryId) {
        PaymentHistory paymentHistory = em.find(PaymentHistory.class, paymentHistoryId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return paymentHistory;
    }

    public List<PaymentHistory> getPaymentHistoryOfUser(Integer userId) {
        TypedQuery<PaymentHistory> query = em.createQuery("SELECT p FROM PaymentHistory p WHERE p.user.id = :userId", PaymentHistory.class);
        query.setParameter("userId", userId);
        List<PaymentHistory> paymentHistories = query.getResultList();
        return paymentHistories;
    }

    public List<PaymentHistory> getPaymentHistoryOfOrder(Integer orderId) {
        TypedQuery<PaymentHistory> query = em.createQuery("SELECT p FROM PaymentHistory p WHERE p.order.id = :orderId", PaymentHistory.class);
        query.setParameter("orderId", orderId);
        List<PaymentHistory> paymentHistories = query.getResultList();
        return paymentHistories;
    }

    public Integer makePayment(PaymentHistory paymentHistory){
        em.persist(paymentHistory);
        em.flush();
        return paymentHistory.getId();
    }
}
