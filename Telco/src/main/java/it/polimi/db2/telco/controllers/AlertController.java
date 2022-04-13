package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Alert;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
import it.polimi.db2.telco.services.AlertService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class AlertController {
    @Inject
    AlertService alertService;
    /*@Inject
    UserService userService;*/

    public AlertController(){}

    /*public Alert getAlertById(Integer alertId) throws AlertException {
        return alertService.getAlertById(alertId);
    }

    public List<Alert> getAlertsByUser(Integer userId) throws UserException, AlertException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return alertService.getAlertsOfUser(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<Alert> getMyAlerts(Integer userId) throws UserException, AlertException {
        return alertService.getAlertsOfUser(userId);
    }*/

    public List<Alert> getAllAlerts() {
        return alertService.getAllAlerts();
    }
}
