package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
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

    public boolean isServicePackageNameAlreadyExist(String name)  {
        TypedQuery<ServicePackage> query = em.createQuery("SELECT s FROM ServicePackage s WHERE s.name = :name", ServicePackage.class);
        query.setParameter("name", name);
        List<ServicePackage> servicePackages = query.getResultList();
        return servicePackages.size() > 0;
    }

    public List<ServicePackage> getAllServicePackages() {
        TypedQuery<ServicePackage> query = em.createQuery("SELECT s FROM ServicePackage s", ServicePackage.class);
        List<ServicePackage> servicePackages = query.getResultList();
        return servicePackages;
    }

    public Integer createServicePackage(ServicePackage servicePackage) {
        List<PackagePrice> packagePrices = servicePackage.getPackagePrices();
        servicePackage.setPackagePrices(new ArrayList<>());
        em.persist(servicePackage);
        em.flush();
        Integer servicePackageId = servicePackage.getId();
        packagePrices.forEach(p -> {
            p.set_package(servicePackage);
            em.persist(p);
            em.flush();
        });
        return servicePackageId;
    }

    /*public Integer updateServicePackage(ServicePackage servicePackage) {
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
    }*/
}
