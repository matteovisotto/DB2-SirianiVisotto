package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.*;
import it.polimi.db2.telco.services.OrderService;
import it.polimi.db2.telco.services.PaymentService;
import it.polimi.db2.telco.services.UserService;

import javax.inject.Inject;
import java.util.List;

public class PaymentController {
    @Inject
    PaymentService paymentService;
    @Inject
    OrderService orderService;
    @Inject
    UserService userService;

    public PaymentController(){}

    public PaymentHistory getPaymentHistoryById(Integer paymentHistoryId) {
        return paymentService.getPaymentById(paymentHistoryId);
    }

    public List<PaymentHistory> getPaymentOfHistoryOfUser(Integer userId) throws Exception {
        User user = userService.getUserById(userId);
        if (user != null) {
            return paymentService.getPaymentHistoryOfUser(userId);
        } else {
            throw new Exception("");
        }
    }

    public List<PaymentHistory> getPaymentOfHistoryOfOrder(Integer orderId) throws Exception {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return paymentService.getPaymentHistoryOfUser(orderId);
        } else {
            throw new Exception("");
        }
    }

    public Integer makeNewPayment(PaymentHistory payment) throws Exception {
        Integer paymentId;
        Order order = payment.getOrder();
        if (order != null) {
            paymentId = paymentService.makePayment(payment);
        } else {
            throw new Exception("");
        }
        return paymentId;
    }
}
