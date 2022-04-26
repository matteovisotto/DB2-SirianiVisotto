package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Alert;
import it.polimi.db2.telco.services.AlertService;

import javax.inject.Inject;
import java.util.List;

public class AlertController {
    @Inject
    AlertService alertService;

    public AlertController(){}

    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}
