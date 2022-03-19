package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotalPurchaseOptional;
import it.polimi.db2.telco.exceptions.report.ReportException;
import it.polimi.db2.telco.services.TotalPurchaseOptionalService;

import javax.inject.Inject;
import java.util.List;

public class TotalPurchaseOptionalController {
    @Inject
    TotalPurchaseOptionalService totalPurchaseOptionalService;

    public TotalPurchaseOptionalController(){}

    public List<TotalPurchaseOptional> getAllTotalPurchaseOptionals() throws ReportException {
        return totalPurchaseOptionalService.getAllTotalPurchaseOptional();
    }
}
