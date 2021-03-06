package it.polimi.db2.telco.services;

import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.exceptions.service.ServiceNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
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

    public boolean isServiceAlreadyExisting(String name) {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s WHERE s.name = :name", Service.class);
        query.setParameter("name", name);
        return query.getResultList().size() == 1;
    }

    public List<Service> getAllServices() throws ServiceNotFoundException {
        TypedQuery<Service> query = em.createQuery("SELECT s FROM Service s", Service.class);
        List<Service> services = query.getResultList();
        return services;
    }

    public Integer createService(Service service) {
        em.persist(service);
        em.flush();
        return service.getId();
    }
}
