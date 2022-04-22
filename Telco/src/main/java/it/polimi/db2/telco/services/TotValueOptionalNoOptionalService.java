package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.TotValueOptionalNoOptional;
import it.polimi.db2.telco.entities.TotalPurchasePackageValidity;
import it.polimi.db2.telco.exceptions.report.TotalPurchasePackageOptionalNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TotValueOptionalNoOptionalService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public TotValueOptionalNoOptionalService(){}

    public List<TotValueOptionalNoOptional> getAllTotValueOptionalNoOptional() {
        TypedQuery<TotValueOptionalNoOptional> query = em.createQuery("SELECT t FROM TotValueOptionalNoOptional t", TotValueOptionalNoOptional.class);
        List<TotValueOptionalNoOptional> totalPurchasePackageValidity = query.getResultList();
        return totalPurchasePackageValidity;
    }
}
