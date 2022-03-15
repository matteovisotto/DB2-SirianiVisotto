package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class OptionalProductService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public OptionalProductService(){}

    public OptionalProduct getOptionalProductById(Integer optionalProductId) throws OptionalProductNotFoundException {
        OptionalProduct optionalProduct = em.find(OptionalProduct.class, optionalProductId);
        if(optionalProduct == null){
            throw new OptionalProductNotFoundException();
        }
        return optionalProduct;
    }

    public OptionalProduct getOptionalProductsByName(String name) throws OptionalProductNotFoundException {
        TypedQuery<OptionalProduct> query = em.createQuery("SELECT o FROM OptionalProduct o WHERE o.name = :name", OptionalProduct.class);
        query.setParameter("name", name);
        OptionalProduct optionalProduct = query.getSingleResult();
        if (optionalProduct == null) {
            throw new OptionalProductNotFoundException();
        }
        return optionalProduct;
    }

    public Integer createOptionalProduct(OptionalProduct optionalProduct) {
        em.persist(optionalProduct);
        em.flush();
        return optionalProduct.getId();
    }

    public Integer updateOptionalProduct(OptionalProduct optionalProduct) {
        optionalProduct = em.merge(optionalProduct);
        em.flush();
        return optionalProduct.getId();
    }


    public void deleteOptionalProduct(OptionalProduct optionalProduct) {
        if (!em.contains(optionalProduct)) {
            optionalProduct = em.merge(optionalProduct);
        }
        em.remove(optionalProduct);
        em.flush();
    }
}
