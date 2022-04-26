package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.InsolventUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class InsolventUserService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public InsolventUserService(){}

    public List<InsolventUser> getAllInsolventUsers() {
        TypedQuery<InsolventUser> query = em.createQuery("SELECT i FROM InsolventUser i", InsolventUser.class);
        return query.getResultList();
    }
}
