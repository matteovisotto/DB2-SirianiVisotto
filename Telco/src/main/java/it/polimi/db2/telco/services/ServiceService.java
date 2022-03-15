package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.exceptions.service.ServiceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ServiceService {
    @PersistenceContext(unitName = "telco-persistence-provider")
    private EntityManager em;

    public ServiceService(){}

    public Service getServiceById(Integer serviceId) throws ServiceNotFoundException {
        Service service = em.find(Service.class, serviceId);
        if(service == null){
            throw new ServiceNotFoundException();
        }
        return service;
    }

    public Service getServiceByName(String name) throws ServiceNotFoundException {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.name = :name", Service.class);
        query.setParameter("name", name);
        Service service = query.getSingleResult();
        if(service == null){
            throw new ServiceNotFoundException();
        }
        return service;
    }

    public Integer createService(Service service) {
        em.persist(service);
        em.flush();
        return service.getId();
    }

    public Integer updateService(Service service) {
        service = em.merge(service);
        em.flush();
        return service.getId();
    }


    public void deleteService(Service service) {
        if (!em.contains(service)) {
            service = em.merge(service);
        }
        em.remove(service);
        em.flush();
    }
}
