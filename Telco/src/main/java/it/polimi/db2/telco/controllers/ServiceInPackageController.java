package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.OptionalProductOrder;
import it.polimi.db2.telco.entities.ServiceInPackage;
import it.polimi.db2.telco.exceptions.serviceInPackage.ServiceInPackageAlreadyExistingException;
import it.polimi.db2.telco.exceptions.serviceInPackage.ServiceInPackageException;
import it.polimi.db2.telco.exceptions.suspendedOrder.SuspendedOrderException;
import it.polimi.db2.telco.services.ServiceInPackageService;

import javax.inject.Inject;

public class ServiceInPackageController {
    @Inject
    ServiceInPackageService serviceInPackageService;

    public ServiceInPackageController(){}

    public ServiceInPackage getServiceInPackageByServiceIdAndPackageId(Integer serviceId, Integer servicePackageId) throws ServiceInPackageException {
        return serviceInPackageService.getServiceInPackageByServiceIdAndPackageId(serviceId, servicePackageId);
    }

    public ServiceInPackage getServiceInPackageByServiceId(Integer serviceId) throws ServiceInPackageException {
        return serviceInPackageService.getServiceInPackageByServiceId(serviceId);
    }

    public ServiceInPackage getServiceInPackageByServicePackageId(Integer servicePackageId) throws ServiceInPackageException {
        return serviceInPackageService.getServiceInPackageByServicePackageId(servicePackageId);
    }

    public void createOptionalProductInPackage(ServiceInPackage serviceInPackage) throws ServiceInPackageException {
        if (serviceInPackageService.getServiceInPackageByServiceIdAndPackageId(serviceInPackage.getService().getId(), serviceInPackage.get_package().getId()) == null) {
            serviceInPackageService.createServiceInPackage(serviceInPackage);
        } else {
            throw new ServiceInPackageAlreadyExistingException();
        }
    }
}
