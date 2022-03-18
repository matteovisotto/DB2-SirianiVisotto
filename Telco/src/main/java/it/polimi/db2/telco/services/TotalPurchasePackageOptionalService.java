package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.TotalPurchasePackageOptional;
import it.polimi.db2.telco.exceptions.materialized.TotalPurchasePackageOptionalNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class TotalPurchasePackageOptionalService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public TotalPurchasePackageOptionalService(){}

    public List<TotalPurchasePackageOptional> getAllTotalPurchasePackageOptional() throws TotalPurchasePackageOptionalNotFoundException {
        TypedQuery<TotalPurchasePackageOptional> query = em.createQuery("SELECT t FROM TotalPurchasePackageOptional t", TotalPurchasePackageOptional.class);
        List<TotalPurchasePackageOptional> totalPurchasePackageOptionals = query.getResultList();
        if(totalPurchasePackageOptionals.size() == 0){
            throw new TotalPurchasePackageOptionalNotFoundException();
        }
        return totalPurchasePackageOptionals;
    }
}
