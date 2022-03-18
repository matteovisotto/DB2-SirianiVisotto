package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.OptionalProductInPackage;
import it.polimi.db2.telco.entities.SuspendedOrder;
import it.polimi.db2.telco.exceptions.optionalProductInPackage.OptionalProductInPackageAlreadyExistingException;
import it.polimi.db2.telco.exceptions.optionalProductInPackage.OptionalProductInPackageException;
import it.polimi.db2.telco.exceptions.suspendedOrder.SuspendedOrderException;
import it.polimi.db2.telco.services.OptionalProductInPackageService;

import javax.inject.Inject;

public class OptionalProductInPackageController {
    @Inject
    OptionalProductInPackageService optionalProductInPackageService;

    public OptionalProductInPackageController(){}

    public OptionalProductInPackage getOptionalProductInPackageByOptionalIdAndPackageId(Integer optionalProductId, Integer packageId) throws OptionalProductInPackageException {
        return optionalProductInPackageService.getOptionalProductInPackageByOptionalProductIdAndPackageId(optionalProductId, packageId);
    }

    public OptionalProductInPackage getOptionalProductInPackageByOptionalProductId(Integer optionalProductId) throws OptionalProductInPackageException {
        return optionalProductInPackageService.getOptionalProductInPackageByOptionalProductId(optionalProductId);
    }

    public OptionalProductInPackage getOptionalProductInPackageByPackageId(Integer packageId) throws OptionalProductInPackageException {
        return optionalProductInPackageService.getOptionalProductInPackageByPackageId(packageId);
    }

    public void createOptionalProductInPackage(OptionalProductInPackage optionalProductInPackage) throws OptionalProductInPackageException {
        if (optionalProductInPackageService.getOptionalProductInPackageByOptionalProductIdAndPackageId(optionalProductInPackage.getOptionalProduct().getId(), optionalProductInPackage.get_package().getId()) == null) {
            optionalProductInPackageService.createOptionalProductInPackage(optionalProductInPackage);
        } else {
            throw new OptionalProductInPackageAlreadyExistingException();
        }
    }
}
