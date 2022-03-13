package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class OptionalProductService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public OptionalProductService(){}

    public OptionalProduct getOptionalProductById(Integer optionalProductId) /*throws ServicePackageNotFound */{
        OptionalProduct optionalProduct = em.find(OptionalProduct.class, optionalProductId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return optionalProduct;
    }

    public OptionalProduct getOptionalProductsByName(String name) {
        TypedQuery<OptionalProduct> query = em.createQuery("SELECT o FROM OptionalProduct o WHERE o.name = :name", OptionalProduct.class);
        query.setParameter("name", name);
        List<OptionalProduct> optionalProducts = query.getResultList();
        return optionalProducts.get(0);
    }

    public Integer createOptionalProduct(OptionalProduct optionalProduct){
        em.persist(optionalProduct);
        em.flush();
        return optionalProduct.getId();
    }

    public Integer updateOptionalProduct(OptionalProduct optionalProduct){
        optionalProduct = em.merge(optionalProduct);
        em.flush();
        return optionalProduct.getId();
    }


    public void deleteOptionalProduct(OptionalProduct optionalProduct){
        if (!em.contains(optionalProduct)) {
            optionalProduct = em.merge(optionalProduct);
        }
        em.remove(optionalProduct);
        em.flush();
    }
}
