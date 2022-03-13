package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Activation;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.services.ActivationService;
import it.polimi.db2.telco.services.ServicePackageService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class ActivationController {
    @Inject
    ActivationService activationService;
    @Inject
    ServicePackageService servicePackageService;
    @Inject
    UserService userService;

    public ActivationController() {
    }

    public Activation getPaymentHistoryById(Integer activationId) {
        return activationService.getActivationById(activationId);
    }

    public List<Activation> getActivationsOfUser(Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user != null) {
            return activationService.getActivationsOfUser(userId);
        } else {
            throw new Exception("");
        }
    }

    public List<Activation> getActivationOfPackage(Integer servicePackageId) throws Exception {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            return activationService.getActivationsOfPackage(servicePackageId);
        } else {
            throw new Exception("");
        }
    }
}