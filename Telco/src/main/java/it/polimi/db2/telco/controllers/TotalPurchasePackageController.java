package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotalPurchasePackage;
import it.polimi.db2.telco.exceptions.report.ReportException;
import it.polimi.db2.telco.services.TotalPurchasePackageService;

import javax.inject.Inject;
import java.util.List;

public class TotalPurchasePackageController {
    @Inject
    TotalPurchasePackageService totalPurchasePackageService;

    public TotalPurchasePackageController(){}

    public List<TotalPurchasePackage> getAllTotalPurchasePackages() throws ReportException {
        return totalPurchasePackageService.getAllTotalPurchasePackages();
    }
}
