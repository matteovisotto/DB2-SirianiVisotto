package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotalPurchasePackageOptional;
import it.polimi.db2.telco.exceptions.report.ReportException;
import it.polimi.db2.telco.services.TotalPurchasePackageOptionalService;

import javax.inject.Inject;
import java.util.List;

public class TotalPurchasePackageOptionalController {
    @Inject
    TotalPurchasePackageOptionalService totalPurchasePackageOptionalService;

    public TotalPurchasePackageOptionalController(){}

    public List<TotalPurchasePackageOptional> getAllTotalPurchasePackageOptionals() throws ReportException {
        return totalPurchasePackageOptionalService.getAllTotalPurchasePackageOptional();
    }
}
