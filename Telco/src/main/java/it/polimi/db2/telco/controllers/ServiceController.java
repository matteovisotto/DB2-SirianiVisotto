package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.services.ServiceService;

import javax.inject.Inject;

public class ServiceController {
    @Inject
    ServiceService serviceService;

    public ServiceController(){}

    public Service getServiceById(Integer serviceId) {
        return serviceService.getServiceById(serviceId);
    }

    public Service getServiceByName(String name) {
        return serviceService.getServiceByName(name);
    }

    public Integer createService(Service service) throws Exception {
        Integer serviceId;
        if (serviceService.getServiceByName(service.getName()) == null) {
            serviceId = serviceService.createService(service);
        } else {
            throw new Exception("");
        }
        return serviceId;
    }

    public Integer updateService(Service service) throws Exception {
        Integer serviceId;
        if (serviceService.getServiceById(service.getId()) != null) {
            serviceId = serviceService.updateService(service);
        } else {
            throw new Exception("");
        }
        return serviceId;
    }

    public void deleteService(Integer serviceId) throws Exception {
        Service service = serviceService.getServiceById(serviceId);
        if (service != null) {
            serviceService.deleteService(service);
        } else {
            throw new Exception("");
        }
    }
}
