/*
package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.OptionalProductInPackage;
import it.polimi.db2.telco.exceptions.optionalProductInPackage.OptionalProductInPackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


public class OptionalProductInPackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public OptionalProductInPackageService(){}

    public OptionalProductInPackage getOptionalProductInPackageByOptionalProductIdAndPackageId(Integer optionalId, Integer packageId) throws OptionalProductInPackageNotFoundException {
        TypedQuery<OptionalProductInPackage> query = em.createQuery("SELECT o FROM OptionalProductInPackage o WHERE o.optionalProduct.id = :optionalId AND o._package.id = :packageId", OptionalProductInPackage.class);
        query.setParameter("optionalId", optionalId);
        query.setParameter("packageId", packageId);
        OptionalProductInPackage optionalProductInPackage = query.getSingleResult();
        if(optionalProductInPackage == null){
            throw new OptionalProductInPackageNotFoundException();
        }
        return optionalProductInPackage;
    }

    public OptionalProductInPackage getOptionalProductInPackageByPackageId(Integer packageId) throws OptionalProductInPackageNotFoundException {
        TypedQuery<OptionalProductInPackage> query = em.createQuery("SELECT o FROM OptionalProductInPackage o WHERE o._package.id = :packageId", OptionalProductInPackage.class);
        query.setParameter("packageId", packageId);
        OptionalProductInPackage optionalProductInPackage = query.getSingleResult();
        if(optionalProductInPackage == null){
            throw new OptionalProductInPackageNotFoundException();
        }
        return optionalProductInPackage;
    }

    public OptionalProductInPackage getOptionalProductInPackageByOptionalProductId(Integer optionalId) throws OptionalProductInPackageNotFoundException {
        TypedQuery<OptionalProductInPackage> query = em.createQuery("SELECT o FROM OptionalProductInPackage o WHERE o.optionalProduct.id = :optionalId", OptionalProductInPackage.class);
        query.setParameter("optionalId", optionalId);
        OptionalProductInPackage optionalProductInPackage = query.getSingleResult();
        if(optionalProductInPackage == null){
            throw new OptionalProductInPackageNotFoundException();
        }
        return optionalProductInPackage;
    }

    public void createOptionalProductInPackage(OptionalProductInPackage optionalProductInPackage) {
        em.persist(optionalProductInPackage);
        em.flush();
    }
}
*/
