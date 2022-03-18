package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.ServiceInPackage;
import it.polimi.db2.telco.exceptions.serviceInPackage.ServiceInPackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ServiceInPackageService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ServiceInPackageService(){}

    public ServiceInPackage getServiceInPackageByServiceIdAndPackageId(Integer serviceId, Integer servicePackageId) throws ServiceInPackageNotFoundException {
        TypedQuery<ServiceInPackage> query = em.createQuery("SELECT s FROM ServiceInPackage s WHERE s.service.id = :serviceId AND s._package.id = :servicePackageId", ServiceInPackage.class);
        query.setParameter("serviceId", serviceId);
        query.setParameter("servicePackageId", servicePackageId);
        ServiceInPackage serviceInPackage = query.getSingleResult();
        if(serviceInPackage == null){
            throw new ServiceInPackageNotFoundException();
        }
        return serviceInPackage;
    }

    public ServiceInPackage getServiceInPackageByServiceId(Integer serviceId) throws ServiceInPackageNotFoundException {
        TypedQuery<ServiceInPackage> query = em.createQuery("SELECT s FROM ServiceInPackage s WHERE s.service.id = :serviceId", ServiceInPackage.class);
        query.setParameter("serviceId", serviceId);
        ServiceInPackage serviceInPackage = query.getSingleResult();
        if(serviceInPackage == null){
            throw new ServiceInPackageNotFoundException();
        }
        return serviceInPackage;
    }

    public ServiceInPackage getServiceInPackageByServicePackageId(Integer servicePackageId) throws ServiceInPackageNotFoundException {
        TypedQuery<ServiceInPackage> query = em.createQuery("SELECT s FROM ServiceInPackage s WHERE s._package.id = :servicePackageId", ServiceInPackage.class);
        query.setParameter("servicePackageId", servicePackageId);
        ServiceInPackage serviceInPackage = query.getSingleResult();
        if(serviceInPackage == null){
            throw new ServiceInPackageNotFoundException();
        }
        return serviceInPackage;
    }

    public void createServiceInPackage(ServiceInPackage serviceInPackage) {
        em.persist(serviceInPackage);
        em.flush();
    }
}
