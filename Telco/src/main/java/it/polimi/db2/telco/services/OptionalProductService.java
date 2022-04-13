package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
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

    /*public OptionalProduct getOptionalProductByName(String name) throws OptionalProductNotFoundException {
        TypedQuery<OptionalProduct> query = em.createQuery("SELECT o FROM OptionalProduct o WHERE o.name = :name", OptionalProduct.class);
        query.setParameter("name", name);
        OptionalProduct optionalProduct = query.getSingleResult();
        if (optionalProduct == null) {
            throw new OptionalProductNotFoundException();
        }
        return optionalProduct;
    }*/

    public boolean isOptionalProductAlreadyExisting(String name) {
        TypedQuery<OptionalProduct> query = em.createQuery("SELECT o FROM OptionalProduct o WHERE o.name = :name", OptionalProduct.class);
        query.setParameter("name", name);
        return query.getResultList().size() == 1;
    }

    public List<OptionalProduct> getAllOptionalProducts() throws OptionalProductNotFoundException {
        TypedQuery<OptionalProduct> query = em.createQuery("SELECT o FROM OptionalProduct o", OptionalProduct.class);
        List<OptionalProduct> optionalProducts = query.getResultList();
        if (optionalProducts == null) {
            throw new OptionalProductNotFoundException();
        }
        return optionalProducts;
    }

    public Integer createOptionalProduct(OptionalProduct optionalProduct) {
        em.persist(optionalProduct);
        em.flush();
        return optionalProduct.getId();
    }

    /*public Integer updateOptionalProduct(OptionalProduct optionalProduct) {
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
    }*/
}
