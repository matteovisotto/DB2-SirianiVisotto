/*
package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.FailedPayment;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.payment.PaymentException;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
import it.polimi.db2.telco.services.FailedPaymentService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class FailedPaymentController {
    @Inject
    FailedPaymentService failedPaymentService;
    @Inject
    UserService userService;

    public FailedPaymentController(){}

    public FailedPayment getFailedPaymentByUserId(Integer failedPaymentId) throws PaymentException {
        return failedPaymentService.getFailedPaymentByUserId(failedPaymentId);
    }

    public List<FailedPayment> getFailedPaymentsOfUser(Integer userId) throws PaymentException, UserException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return failedPaymentService.getFailedPaymentsOfUser(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<FailedPayment> getAllFailedPaymentsPerUser() throws PaymentException {
        return failedPaymentService.getAllFailedPaymentsPerUser();
    }
}
*/
