package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Administrator;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class AdministratorService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public AdministratorService(){}

    public Administrator getAdministratorByEmail(String email) {
        TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a WHERE a.email = :email", Administrator.class);
        query.setParameter("email", email);
        List<Administrator> administrators = query.getResultList();
        if(administrators.size() != 1){
            return null;
        }
        return administrators.get(0);
    }
}
