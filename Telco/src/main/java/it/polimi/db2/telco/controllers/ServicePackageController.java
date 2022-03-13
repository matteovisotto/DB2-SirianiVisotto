package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.ServicePackage;
 import it.polimi.db2.telco.services.ServicePackageService;

import javax.inject.Inject;

public class ServicePackageController {
    @Inject
    ServicePackageService servicePackageService;

    public ServicePackageController(){}

    public ServicePackage getServicePackageById(Integer servicePackageId) {
        return servicePackageService.getServicePackageById(servicePackageId);
    }

    public ServicePackage getServicePackageByName(String name) {
        return servicePackageService.getServicePackageByName(name);
    }

    public Integer createServicePackage(ServicePackage servicePackage) throws Exception {
        Integer servicePackageId;
        if (servicePackageService.getServicePackageByName(servicePackage.getName()) == null) {
            servicePackageId = servicePackageService.updateServicePackage(servicePackage);
        } else {
            throw new Exception("");
        }
        return servicePackageId;
    }

    public Integer updateServicePackage(ServicePackage servicePackage) throws Exception {
        Integer servicePackageId;
        if (servicePackageService.getServicePackageById(servicePackage.getId()) != null) {
            servicePackageId = servicePackageService.updateServicePackage(servicePackage);
        } else {
            throw new Exception("");
        }
        return servicePackageId;
    }

    public void deleteServicePackage(Integer servicePackageId) throws Exception {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            servicePackageService.deleteServicePackage(servicePackage);
        } else {
            throw new Exception("");
        }
    }
}
