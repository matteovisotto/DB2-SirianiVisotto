package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.*;
import it.polimi.db2.telco.exceptions.order.OrderAlreadyPayedException;
import it.polimi.db2.telco.exceptions.order.OrderException;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;
import it.polimi.db2.telco.exceptions.payment.PaymentException;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
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

    public PaymentHistory getPaymentHistoryById(Integer paymentHistoryId) throws PaymentException {
        return paymentService.getPaymentById(paymentHistoryId);
    }

    public List<PaymentHistory> getPaymentOfHistoryOfUser(Integer userId) throws PaymentException, UserException {
        User user = userService.getUserById(userId);
        if (user != null) {
            return paymentService.getPaymentHistoryOfUser(userId);
        } else {
            throw new UserNotFoundException();
        }
    }

    public List<PaymentHistory> getPaymentOfHistoryOfOrder(Integer orderId) throws PaymentException, OrderException {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            return paymentService.getPaymentHistoryOfOrder(orderId);
        } else {
            throw new OrderNotFoundException();
        }
    }

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