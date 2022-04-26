package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.exceptions.service.ServiceAlreadyExistingException;
import it.polimi.db2.telco.exceptions.service.ServiceException;
import it.polimi.db2.telco.services.ServiceService;

import javax.inject.Inject;
import java.util.List;

public class ServiceController {
    @Inject
    ServiceService serviceService;

    public ServiceController(){}

    public Service getServiceById(Integer serviceId) throws ServiceException {
        return serviceService.getServiceById(serviceId);
    }

    public Service getServiceByName(String name) throws ServiceException {
        return serviceService.getServiceByName(name);
    }

    public List<Service> getAllServices() throws ServiceException {
        return serviceService.getAllServices();
    }

    public Integer createService(Service service) throws ServiceException {
        Integer serviceId;
        if (!serviceService.isServiceAlreadyExisting(service.getName())) {
            serviceId = serviceService.createService(service);
        } else {
            throw new ServiceAlreadyExistingException();
        }
        return serviceId;
    }
}
