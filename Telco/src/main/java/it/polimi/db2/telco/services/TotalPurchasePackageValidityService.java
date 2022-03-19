package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.TotalPurchasePackageValidity;
import it.polimi.db2.telco.exceptions.report.TotalPurchasePackageOptionalNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class TotalPurchasePackageValidityService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public TotalPurchasePackageValidityService(){}

    public List<TotalPurchasePackageValidity> getAllTotalPurchasePackageValidity() throws TotalPurchasePackageOptionalNotFoundException {
        TypedQuery<TotalPurchasePackageValidity> query = em.createQuery("SELECT t FROM TotalPurchasePackageValidity t", TotalPurchasePackageValidity.class);
        List<TotalPurchasePackageValidity> totalPurchasePackageValidity = query.getResultList();
        if(totalPurchasePackageValidity.size() == 0){
            throw new TotalPurchasePackageOptionalNotFoundException();
        }
        return totalPurchasePackageValidity;
    }
}
