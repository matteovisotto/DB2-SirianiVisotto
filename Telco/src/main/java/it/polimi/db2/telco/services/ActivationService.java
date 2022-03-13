package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Activation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActivationService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ActivationService(){}

    public Activation getActivationById(Integer activationId) /*throws ServicePackageNotFound */{
        Activation activation = em.find(Activation.class, activationId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return activation;
    }

    public List<Activation> getActivationsOfUser(Integer userId) {
        TypedQuery<Activation> query = em.createQuery("SELECT a FROM Activation a WHERE a.userId = :userId", Activation.class);
        query.setParameter("userId", userId);
        List<Activation> activations = query.getResultList();
        return activations;
    }

    public List<Activation> getActivationsOfPackage(Integer packageId) {
        TypedQuery<Activation> query = em.createQuery("SELECT a FROM Activation a WHERE a.packageId = :packageId", Activation.class);
        query.setParameter("packageId", packageId);
        List<Activation> activations = query.getResultList();
        return activations;
    }
}
