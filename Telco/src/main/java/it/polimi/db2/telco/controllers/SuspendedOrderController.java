package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.SuspendedOrder;
import it.polimi.db2.telco.exceptions.suspendedOrder.SuspendedOrderException;
import it.polimi.db2.telco.services.SuspendedOrderService;

import javax.inject.Inject;
import java.util.List;

public class SuspendedOrderController {
    @Inject
    SuspendedOrderService suspendedOrderService;

    public SuspendedOrderController(){}

    public SuspendedOrder getSuspendedOrderByOrderIdAndUserId(Integer orderId, Integer userId) throws SuspendedOrderException {
        return suspendedOrderService.getSuspendedOrderByOrderIdAndUserId(orderId, userId);
    }

    public List<SuspendedOrder> getMySuspendedOrders(Integer userId) throws SuspendedOrderException {
        return suspendedOrderService.getMySuspendedOrders(userId);
    }

    public List<SuspendedOrder> getAllSuspendedOrders() throws SuspendedOrderException {
        return suspendedOrderService.getAllSuspendedOrders();
    }
}
