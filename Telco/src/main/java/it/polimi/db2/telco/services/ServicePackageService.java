package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServicePackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ServicePackageService(){}

    public ServicePackage getServicePackageById(Integer servicePackageId) throws ServicePackageNotFoundException {
        ServicePackage servicePackage = em.find(ServicePackage.class, servicePackageId);
        if(servicePackage == null){
            throw new ServicePackageNotFoundException();
        }
        return servicePackage;
    }

    public ServicePackage getServicePackageByName(String name) throws ServicePackageNotFoundException {
        TypedQuery<ServicePackage> query = em.createQuery("SELECT s FROM ServicePackage s WHERE s.name = :name", ServicePackage.class);
        query.setParameter("name", name);
        List<ServicePackage> servicePackages = query.getResultList();
        if(servicePackages.size() != 1){
            throw new ServicePackageNotFoundException();
        }
        return servicePackages.get(0);
    }

    public List<ServicePackage> getAllServicePackages() {
        TypedQuery<ServicePackage> query = em.createQuery("SELECT s FROM ServicePackage s", ServicePackage.class);
        List<ServicePackage> servicePackages = query.getResultList();
        return servicePackages;
    }

    public Integer createServicePackage(ServicePackage servicePackage) {
        em.persist(servicePackage);
        em.flush();
        return servicePackage.getId();
    }

    public Integer updateServicePackage(ServicePackage servicePackage) {
        servicePackage = em.merge(servicePackage);
        em.flush();
        return servicePackage.getId();
    }


    public void deleteServicePackage(ServicePackage servicePackage) {
        if (!em.contains(servicePackage)) {
            servicePackage = em.merge(servicePackage);
        }
        em.remove(servicePackage);
        em.flush();
    }
}
