package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotalPurchasePackageValidity;
import it.polimi.db2.telco.exceptions.report.ReportException;
import it.polimi.db2.telco.services.TotalPurchasePackageValidityService;

import javax.inject.Inject;
import java.util.List;

public class TotalPurchasePackageValidityController {
    @Inject
    TotalPurchasePackageValidityService totalPurchasePackageValidityService;

    public TotalPurchasePackageValidityController(){}

    public List<TotalPurchasePackageValidity> getAllTotalPurchasePackageValidity() throws ReportException {
        return totalPurchasePackageValidityService.getAllTotalPurchasePackageValidity();
    }
}
