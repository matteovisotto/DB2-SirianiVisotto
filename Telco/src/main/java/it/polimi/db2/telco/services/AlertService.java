package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Alert;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AlertService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public AlertService(){}

    public List<Alert> getAllAlerts() {
        TypedQuery<Alert> query = em.createQuery("SELECT a FROM Alert a", Alert.class);
        return query.getResultList();
    }
}
