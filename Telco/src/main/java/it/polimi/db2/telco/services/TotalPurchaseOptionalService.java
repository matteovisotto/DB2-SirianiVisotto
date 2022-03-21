package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.TotalPurchaseOptional;
import it.polimi.db2.telco.exceptions.report.TotalPurchaseOptionalNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TotalPurchaseOptionalService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public TotalPurchaseOptionalService(){}

    public List<TotalPurchaseOptional> getAllTotalPurchaseOptional() throws TotalPurchaseOptionalNotFoundException {
        TypedQuery<TotalPurchaseOptional> query = em.createQuery("SELECT t FROM TotalPurchaseOptional t", TotalPurchaseOptional.class);
        List<TotalPurchaseOptional> totalPurchaseOptionals = query.getResultList();
        if(totalPurchaseOptionals.size() == 0){
            throw new TotalPurchaseOptionalNotFoundException();
        }
        return totalPurchaseOptionals;
    }
}
