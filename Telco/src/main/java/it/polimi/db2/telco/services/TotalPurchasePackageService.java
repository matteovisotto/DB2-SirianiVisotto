package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.TotalPurchasePackage;
import it.polimi.db2.telco.exceptions.materialized.TotalPurchasePackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class TotalPurchasePackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public TotalPurchasePackageService(){}

    public List<TotalPurchasePackage> getAllTotalPurchasePackages() throws TotalPurchasePackageNotFoundException {
        TypedQuery<TotalPurchasePackage> query = em.createQuery("SELECT t FROM TotalPurchasePackage t", TotalPurchasePackage.class);
        List<TotalPurchasePackage> totalPurchasePackages = query.getResultList();
        if(totalPurchasePackages.size() == 0){
            throw new TotalPurchasePackageNotFoundException();
        }
        return totalPurchasePackages;
    }
}
