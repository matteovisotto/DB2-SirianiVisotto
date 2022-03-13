package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.ServicePackage;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServicePackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ServicePackageService(){}

    public ServicePackage getServicePackageById(Integer servicePackageId) /*throws ServicePackageNotFound */{
        ServicePackage servicePackage = em.find(ServicePackage.class, servicePackageId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return servicePackage;
    }

    public ServicePackage getServicePackageByName(String name) {
        TypedQuery<ServicePackage> query = em.createQuery("SELECT s FROM ServicePackage s WHERE s.name = :name", ServicePackage.class);
        query.setParameter("name", name);
        List<ServicePackage> servicePackages = query.getResultList();
/*        if(servicePackages.size() != 1){
            throw new UserNotFoundException();
        }*/
        return servicePackages.get(0);
    }

    public Integer createServicePackage(ServicePackage servicePackage){
        em.persist(servicePackage);
        em.flush();
        return servicePackage.getId();
    }

    public Integer updateServicePackage(ServicePackage servicePackage){
        servicePackage = em.merge(servicePackage);
        em.flush();
        return servicePackage.getId();
    }


    public void deleteServicePackage(ServicePackage servicePackage){
        if (!em.contains(servicePackage)) {
            servicePackage = em.merge(servicePackage);
        }
        em.remove(servicePackage);
        em.flush();
    }
}
