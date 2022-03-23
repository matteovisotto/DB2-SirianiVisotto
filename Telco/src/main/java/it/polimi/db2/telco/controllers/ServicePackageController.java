package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageAlreadyExistingException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageNotFoundException;
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

    public ServicePackage getServicePackageByName(String name) throws ServicePackageException {
        return servicePackageService.getServicePackageByName(name);
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

    public Integer updateServicePackage(ServicePackage servicePackage) throws ServicePackageException {
        Integer servicePackageId;
        if (servicePackageService.getServicePackageById(servicePackage.getId()) != null) {
            servicePackageId = servicePackageService.updateServicePackage(servicePackage);
        } else {
            throw new ServicePackageNotFoundException();
        }
        return servicePackageId;
    }

    public void deleteServicePackage(Integer servicePackageId) throws ServicePackageException {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            servicePackageService.deleteServicePackage(servicePackage);
        } else {
            throw new ServicePackageNotFoundException();
        }
    }
}
