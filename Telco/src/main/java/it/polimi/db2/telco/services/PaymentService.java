package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PaymentHistory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
public class PaymentService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public PaymentService(){}

    public Integer makePayment(PaymentHistory paymentHistory) {
        em.persist(paymentHistory);
        em.flush();
        return paymentHistory.getId();
    }
}
