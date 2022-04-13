/*
package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Activation;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.activation.ActivationException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageNotFoundException;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
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

    public Activation getActivationByOrderId(Integer activationId) throws ActivationException {
        return activationService.getActivationById(activationId);
    }

    public List<Activation> getActivationsOfUser(Integer userId) throws ActivationException, UserException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return activationService.getActivationsOfUser(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<Activation> getActivationOfPackage(Integer servicePackageId) throws ActivationException, ServicePackageException {
        ServicePackage servicePackage = servicePackageService.getServicePackageById(servicePackageId);
        if (servicePackage != null) {
            return activationService.getActivationsOfPackage(servicePackageId);
        } else {
            throw new ServicePackageNotFoundException();
        }
    }
}*/
