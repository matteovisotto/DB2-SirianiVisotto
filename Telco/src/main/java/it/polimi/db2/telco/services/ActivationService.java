package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Activation;
import it.polimi.db2.telco.exceptions.activation.ActivationNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ActivationService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ActivationService(){}

    public Activation getActivationById(Integer activationId) throws ActivationNotFoundException {
        Activation activation = em.find(Activation.class, activationId);
        if(activation == null){
            throw new ActivationNotFoundException();
        }
        return activation;
    }

    public List<Activation> getActivationsOfUser(Integer userId) throws ActivationNotFoundException {
        TypedQuery<Activation> query = em.createQuery("SELECT a FROM Activation a WHERE a.userId = :userId", Activation.class);
        query.setParameter("userId", userId);
        List<Activation> activations = query.getResultList();
        if (activations.size() == 0) {
            throw new ActivationNotFoundException();
        }
        return activations;
    }

    public List<Activation> getActivationsOfPackage(Integer packageId) throws ActivationNotFoundException {
        TypedQuery<Activation> query = em.createQuery("SELECT a FROM Activation a WHERE a.packageId = :packageId", Activation.class);
        query.setParameter("packageId", packageId);
        List<Activation> activations = query.getResultList();
        if (activations.size() == 0) {
            throw new ActivationNotFoundException();
        }
        return activations;
    }
}
