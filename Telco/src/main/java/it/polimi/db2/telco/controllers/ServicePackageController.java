package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageAlreadyExistingException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;
import it.polimi.db2.telco.services.ServicePackageService;

import javax.inject.Inject;
import java.util.List;

public class ServicePackageController {
    @Inject
    ServicePackageService servicePackageService;

    public ServicePackageController(){}

    public ServicePackage getServicePackageById(Integer servicePackageId) throws ServicePackageException {
        return servicePackageService.getServicePackageById(servicePackageId);
    }

    public List<ServicePackage> getAllServicePackages() {
        return servicePackageService.getAllServicePackages();
    }

    public Integer createServicePackage(ServicePackage servicePackage) throws ServicePackageException {
        Integer servicePackageId;
        if(!servicePackageService.isServicePackageNameAlreadyExist(servicePackage.getName())) {
            servicePackageId = servicePackageService.createServicePackage(servicePackage);
        } else {
            throw new ServicePackageAlreadyExistingException();
        }
        return servicePackageId;
    }
}
