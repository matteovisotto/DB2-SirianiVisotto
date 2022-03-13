package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.ServicePackage;
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

    public PackagePrice getPackagePriceById(Integer orderId) {
        return packagePriceService.getPackagePriceById(orderId);
    }

    public List<PackagePrice> getPackagePricesOfPackage(Integer servicePackageId) throws Exception {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            return packagePriceService.getPackagePricesOfPackage(servicePackageId);
        } else {
            throw new Exception("");
        }
    }

    public List<PackagePrice> getPricesByValidityPeriodAndPackage(Integer validityPeriod, Integer packageId) throws Exception {
        if (validityPeriod == 12 || validityPeriod == 24 || validityPeriod == 36) {
            return packagePriceService.getPackagePricesOfPackage(packageId);
        } else {
            throw new Exception("");
        }
    }

    public Integer createPackagePrice(PackagePrice packagePrice) throws Exception {
        Integer packagePriceId;
        if ((packagePriceService.getPriceByValidityPeriodAndPackage(packagePrice.getValidityPeriod(), packagePrice.get_package().getId()) == null) && (packagePrice.getValidityPeriod() == 12 || packagePrice.getValidityPeriod() == 24 || packagePrice.getValidityPeriod() == 36)){
            packagePriceId = packagePriceService.createPackagePrice(packagePrice);
        } else {
            throw new Exception("");
        }
        return packagePriceId;
    }

    public Integer updatePackagePrice(PackagePrice packagePrice) throws Exception {
        Integer packagePriceId;
        if (packagePriceService.getPriceByValidityPeriodAndPackage(packagePrice.getValidityPeriod(), packagePrice.get_package().getId()) != null){
            packagePriceId = packagePriceService.updatePackagePrice(packagePrice);
        } else {
            throw new Exception("");
        }
        return packagePriceId;
    }

    public void deletePackagePrice(Integer packagePriceId) throws Exception {
        PackagePrice packagePrice = packagePriceService.getPackagePriceById(packagePriceId);
        if (packagePrice != null){
            packagePriceService.deletePackagePrice(packagePrice);
        } else {
            throw new Exception("");
        }
    }
}
