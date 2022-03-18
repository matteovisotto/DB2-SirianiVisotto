package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotalPurchasePackageValidity;
import it.polimi.db2.telco.exceptions.materialized.MaterializedException;
import it.polimi.db2.telco.services.TotalPurchasePackageValidityService;

import javax.inject.Inject;
import java.util.List;

public class TotalPurchasePackageValidityController {
    @Inject
    TotalPurchasePackageValidityService totalPurchasePackageValidityService;

    public TotalPurchasePackageValidityController(){}

    public List<TotalPurchasePackageValidity> getAllTotalPurchasePackageValidity() throws MaterializedException {
        return totalPurchasePackageValidityService.getAllTotalPurchasePackageValidity();
    }
}
