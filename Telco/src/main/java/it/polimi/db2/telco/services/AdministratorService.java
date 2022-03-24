package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Administrator;
import it.polimi.db2.telco.exceptions.administrator.AdministratorNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class AdministratorService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public AdministratorService(){}

    public Administrator getAdministratorById(Integer administratorId) throws AdministratorNotFoundException {
        Administrator administrator = em.find(Administrator.class, administratorId);
        if(administrator == null){
            throw new AdministratorNotFoundException();
        }
        return administrator;
    }

    public Administrator getAdministratorByEmail(String email) {
        TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a WHERE a.email = :email", Administrator.class);
        query.setParameter("email", email);
        List<Administrator> administrators = query.getResultList();
        if(administrators.size() != 1){
            return null;
        }
        return administrators.get(0);
    }

    public List<Administrator> getAdministratorsByRole(String role) throws AdministratorNotFoundException {
        TypedQuery<Administrator> query = em.createQuery("SELECT a FROM Administrator a WHERE a.role = :role", Administrator.class);
        query.setParameter("role", role);
        List<Administrator> administrators = query.getResultList();
        if (administrators.size() == 0) {
            throw new AdministratorNotFoundException();
        }
        return administrators;
    }

    public Integer createAdministrator(Administrator administrator) {
        em.persist(administrator);
        em.flush();
        return administrator.getId();
    }

    public Integer updateAdministrator(Administrator administrator) {
        administrator = em.merge(administrator);
        em.flush();
        return administrator.getId();
    }


    public void deleteAdministrator(Administrator administrator) {
        if (!em.contains(administrator)) {
            administrator = em.merge(administrator);
        }
        em.remove(administrator);
        em.flush();
    }
}
