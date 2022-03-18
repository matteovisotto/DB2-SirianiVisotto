package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.AveragePurchaseOptionalPackage;
import it.polimi.db2.telco.exceptions.materialized.MaterializedException;
import it.polimi.db2.telco.services.AveragePurchaseOptionalPackageService;

import javax.inject.Inject;
import java.util.List;

public class AveragePurchaseOptionalPackageController {
    @Inject
    AveragePurchaseOptionalPackageService averagePurchaseOptionalPackageService;

    public AveragePurchaseOptionalPackageController(){}

    public List<AveragePurchaseOptionalPackage> getAllAveragePurchaseOptionalPackage() throws MaterializedException {
        return averagePurchaseOptionalPackageService.getAllAveragePurchaseOptionalPackages();
    }
}