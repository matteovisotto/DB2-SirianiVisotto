package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.FailedPayment;
import it.polimi.db2.telco.exceptions.payment.PaymentNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class FailedPaymentService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public FailedPaymentService(){}

    public FailedPayment getFailedPaymentByUserId(Integer failedPaymentId) throws PaymentNotFoundException {
        FailedPayment failedPayment = em.find(FailedPayment.class, failedPaymentId);
        if(failedPayment == null){
            throw new PaymentNotFoundException();
        }
        return failedPayment;
    }

    public List<FailedPayment> getFailedPaymentsOfUser(Integer userId) throws PaymentNotFoundException {
        TypedQuery<FailedPayment> query = em.createQuery("SELECT a FROM FailedPayment a WHERE a.id = :userId", FailedPayment.class);
        query.setParameter("userId", userId);
        List<FailedPayment> failedPayments = query.getResultList();
        if (failedPayments.size() == 0) {
            throw new PaymentNotFoundException();
        }
        return failedPayments;
    }

    public List<FailedPayment> getAllFailedPaymentsPerUser() throws PaymentNotFoundException {
        TypedQuery<FailedPayment> query = em.createQuery("SELECT a FROM FailedPayment a", FailedPayment.class);
        List<FailedPayment> failedPayments = query.getResultList();
        if (failedPayments.size() == 0) {
            throw new PaymentNotFoundException();
        }
        return failedPayments;
    }
}
