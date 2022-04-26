package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.*;
import it.polimi.db2.telco.exceptions.order.OrderAlreadyPayedException;
import it.polimi.db2.telco.exceptions.order.OrderException;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;
import it.polimi.db2.telco.services.PaymentService;

import javax.inject.Inject;

public class PaymentController {
    @Inject
    PaymentService paymentService;

    public PaymentController(){}

    public Integer makeNewPayment(PaymentHistory payment) throws OrderException {
        Integer paymentId;
        Order order = payment.getOrder();
        if (order != null) {
            if (order.getOrderStatus() != 1) {
                paymentId = paymentService.makePayment(payment);
            } else {
                throw new OrderAlreadyPayedException();
            }
        } else {
            throw new OrderNotFoundException();
        }
        return paymentId;
    }
}
