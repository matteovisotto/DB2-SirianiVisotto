package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class ServiceService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ServiceService(){}

    public Service getServiceById(Integer serviceId) /*throws ServicePackageNotFound */{
        Service service = em.find(Service.class, serviceId);
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return service;
    }

    public Service getServiceByName(String name) /*throws ServicePackageNotFound */{
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.name = :name", Service.class);
        query.setParameter("name", name);
        List<Service> services = query.getResultList();
/*        if(servicePackage == null){
            throw new ServicePackageNotFound();
        }*/
        return services.get(0);
    }

    public Integer createService(Service service){
        em.persist(service);
        em.flush();
        return service.getId();
    }

    public Integer updateService(Service service){
        service = em.merge(service);
        em.flush();
        return service.getId();
    }


    public void deleteService(Service service){
        if (!em.contains(service)) {
            service = em.merge(service);
        }
        em.remove(service);
        em.flush();
    }
}
