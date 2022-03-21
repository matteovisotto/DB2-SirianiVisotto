package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Alert;
import it.polimi.db2.telco.exceptions.alert.AlertNotFoundException;

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

    public Alert getAlertById(Integer alertId) throws AlertNotFoundException {
        Alert alert = em.find(Alert.class, alertId);
        if(alert == null){
            throw new AlertNotFoundException();
        }
        return alert;
    }

    public List<Alert> getAlertsOfUser(Integer userId) throws AlertNotFoundException {
        TypedQuery<Alert> query = em.createQuery("SELECT a FROM Alert a WHERE a.user.id = :userId", Alert.class);
        query.setParameter("userId", userId);
        List<Alert> alerts = query.getResultList();
        if (alerts.size() == 0) {
            throw new AlertNotFoundException();
        }
        return alerts;
    }

    public List<Alert> getAllAlerts() throws AlertNotFoundException {
        TypedQuery<Alert> query = em.createQuery("SELECT a FROM Alert a", Alert.class);
        List<Alert> alerts = query.getResultList();
        if (alerts.size() == 0) {
            throw new AlertNotFoundException();
        }
        return alerts;
    }
}
