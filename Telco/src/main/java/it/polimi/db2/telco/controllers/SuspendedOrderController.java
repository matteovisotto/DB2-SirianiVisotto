package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.SuspendedOrder;
import it.polimi.db2.telco.services.SuspendedOrderService;

import javax.inject.Inject;
import java.util.List;

public class SuspendedOrderController {
    @Inject
    SuspendedOrderService suspendedOrderService;

    @Inject
    OrderController orderController;

    public SuspendedOrderController(){}

    public List<SuspendedOrder> getAllSuspendedOrders() {
        return suspendedOrderService.getAllSuspendedOrders();
    }

    public Order toOrder(SuspendedOrder suspendedOrder) {
        return orderController.getOrderById(suspendedOrder.getId().getOrderId());
    }
}
