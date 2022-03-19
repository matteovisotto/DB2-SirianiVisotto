package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.AveragePurchaseOptionalPackage;
import it.polimi.db2.telco.exceptions.report.AveragePurchaseOptionalPackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class AveragePurchaseOptionalPackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public AveragePurchaseOptionalPackageService(){}

    public List<AveragePurchaseOptionalPackage> getAllAveragePurchaseOptionalPackages() throws AveragePurchaseOptionalPackageNotFoundException {
        TypedQuery<AveragePurchaseOptionalPackage> query = em.createQuery("SELECT a FROM AveragePurchaseOptionalPackage a", AveragePurchaseOptionalPackage.class);
        List<AveragePurchaseOptionalPackage> averagePurchaseOptionalPackages = query.getResultList();
        if(averagePurchaseOptionalPackages.size() == 0){
            throw new AveragePurchaseOptionalPackageNotFoundException();
        }
        return averagePurchaseOptionalPackages;
    }
}
