package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PaymentHistory;
import it.polimi.db2.telco.exceptions.payment.PaymentNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class PaymentService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public PaymentService(){}

    public PaymentHistory getPaymentById(Integer paymentHistoryId) throws PaymentNotFoundException {
        PaymentHistory paymentHistory = em.find(PaymentHistory.class, paymentHistoryId);
        if(paymentHistory == null){
            throw new PaymentNotFoundException();
        }
        return paymentHistory;
    }

    public List<PaymentHistory> getPaymentHistoryOfUser(Integer userId) throws PaymentNotFoundException {
        TypedQuery<PaymentHistory> query = em.createQuery("SELECT p FROM PaymentHistory p WHERE p.user.id = :userId", PaymentHistory.class);
        query.setParameter("userId", userId);
        List<PaymentHistory> paymentHistories = query.getResultList();
        if (paymentHistories.size() == 0) {
            throw new PaymentNotFoundException();
        }
        return paymentHistories;
    }

    public List<PaymentHistory> getPaymentHistoryOfOrder(Integer orderId) throws PaymentNotFoundException {
        TypedQuery<PaymentHistory> query = em.createQuery("SELECT p FROM PaymentHistory p WHERE p.order.id = :orderId", PaymentHistory.class);
        query.setParameter("orderId", orderId);
        List<PaymentHistory> paymentHistories = query.getResultList();
        if (paymentHistories.size() == 0) {
            throw new PaymentNotFoundException();
        }
        return paymentHistories;
    }

    public List<PaymentHistory> getPaymentHistoryOfMyOrder(Integer orderId, Integer userId) throws PaymentNotFoundException {
        TypedQuery<PaymentHistory> query = em.createQuery("SELECT p FROM PaymentHistory p WHERE p.order.id = :orderId AND p.user.id = :userId", PaymentHistory.class);
        query.setParameter("orderId", orderId);
        query.setParameter("userId", userId);
        List<PaymentHistory> paymentHistories = query.getResultList();
        if (paymentHistories.size() == 0) {
            throw new PaymentNotFoundException();
        }
        return paymentHistories;
    }

    public Integer makePayment(PaymentHistory paymentHistory) {
        em.persist(paymentHistory);
        em.flush();
        return paymentHistory.getId();
    }
}
