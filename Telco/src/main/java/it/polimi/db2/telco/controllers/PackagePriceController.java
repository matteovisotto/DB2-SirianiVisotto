package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.packagePrice.PackagePriceAlreadyExistingException;
import it.polimi.db2.telco.exceptions.packagePrice.PackagePriceException;
import it.polimi.db2.telco.exceptions.packagePrice.PackagePriceNotFoundException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageNotFoundException;
import it.polimi.db2.telco.exceptions.validityPeriod.InvalidValidityPeriodException;
import it.polimi.db2.telco.exceptions.validityPeriod.ValidityPeriodException;
import it.polimi.db2.telco.services.PackagePriceService;
import it.polimi.db2.telco.services.ServicePackageService;

import javax.inject.Inject;
import java.util.List;

public class PackagePriceController {
    @Inject
    PackagePriceService packagePriceService;
    @Inject
    ServicePackageService servicePackageService;

    public PackagePriceController(){}

    public PackagePrice getPackagePriceById(Integer orderId) throws PackagePriceException {
        return packagePriceService.getPackagePriceById(orderId);
    }

    public List<PackagePrice> getPackagePricesOfPackage(Integer servicePackageId) throws PackagePriceException, ServicePackageException {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            return packagePriceService.getPackagePricesOfPackage(servicePackageId);
        } else {
            throw new ServicePackageNotFoundException();
        }
    }

    public Integer createPackagePrice(PackagePrice packagePrice) throws PackagePriceException {
        Integer packagePriceId;
        if ((packagePriceService.getPriceByValidityPeriodAndPackage(packagePrice.getValidityPeriod(), packagePrice.get_package().getId()) == null) && (packagePrice.getValidityPeriod() == 12 || packagePrice.getValidityPeriod() == 24 || packagePrice.getValidityPeriod() == 36)){
            packagePriceId = packagePriceService.createPackagePrice(packagePrice);
        } else {
            throw new PackagePriceAlreadyExistingException();
        }
        return packagePriceId;
    }

    public Integer updatePackagePrice(PackagePrice packagePrice) throws PackagePriceException {
        Integer packagePriceId;
        if (packagePriceService.getPriceByValidityPeriodAndPackage(packagePrice.getValidityPeriod(), packagePrice.get_package().getId()) != null){
            packagePriceId = packagePriceService.updatePackagePrice(packagePrice);
        } else {
            throw new PackagePriceNotFoundException();
        }
        return packagePriceId;
    }

    public void deletePackagePrice(Integer packagePriceId) throws PackagePriceException {
        PackagePrice packagePrice = packagePriceService.getPackagePriceById(packagePriceId);
        if (packagePrice != null){
            packagePriceService.deletePackagePrice(packagePrice);
        } else {
            throw new PackagePriceNotFoundException();
        }
    }
}
