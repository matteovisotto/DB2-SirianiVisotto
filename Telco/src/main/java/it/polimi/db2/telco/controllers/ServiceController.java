package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.exceptions.service.ServiceAlreadyExistingException;
import it.polimi.db2.telco.exceptions.service.ServiceException;
import it.polimi.db2.telco.exceptions.service.ServiceNotFoundException;
import it.polimi.db2.telco.services.ServiceService;

import javax.inject.Inject;

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

    public Integer createService(Service service) throws ServiceException {
        Integer serviceId;
        if (serviceService.getServiceByName(service.getName()) == null) {
            serviceId = serviceService.createService(service);
        } else {
            throw new ServiceAlreadyExistingException();
        }
        return serviceId;
    }

    public Integer updateService(Service service) throws ServiceException {
        Integer serviceId;
        if (serviceService.getServiceById(service.getId()) != null) {
            serviceId = serviceService.updateService(service);
        } else {
            throw new ServiceNotFoundException();
        }
        return serviceId;
    }

    public void deleteService(Integer serviceId) throws ServiceException {
        Service service = serviceService.getServiceById(serviceId);
        if (service != null) {
            serviceService.deleteService(service);
        } else {
            throw new ServiceNotFoundException();
        }
    }
}
