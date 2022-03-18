package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.OptionalProductInPackage;
import it.polimi.db2.telco.entities.OptionalProductOrder;
import it.polimi.db2.telco.exceptions.optionalProductOrder.OptionalProductOrderAlreadyExistingException;
import it.polimi.db2.telco.exceptions.optionalProductOrder.OptionalProductOrderException;
import it.polimi.db2.telco.exceptions.suspendedOrder.SuspendedOrderException;
import it.polimi.db2.telco.services.OptionalProductOrderService;

import javax.inject.Inject;

public class OptionalProductOrderController {
    @Inject
    OptionalProductOrderService optionalProductOrderService;

    public OptionalProductOrderController(){}

    public OptionalProductOrder getOptionalProductOrderByOptionalIdAndOrderId(Integer optionalProductId, Integer orderId) throws OptionalProductOrderException {
        return optionalProductOrderService.getOptionalProductOrderByOptionalProductIdAndOrderId(optionalProductId, orderId);
    }

    public OptionalProductOrder getOptionalProductInPackageByOptionalProductId(Integer optionalProductId) throws OptionalProductOrderException {
        return optionalProductOrderService.getOptionalProductOrderByOptionalProductId(optionalProductId);
    }

    public OptionalProductOrder getOptionalProductInPackageByOrderId(Integer packageId) throws OptionalProductOrderException {
        return optionalProductOrderService.getOptionalProductOrderByOrderId(packageId);
    }

    public void createOptionalProductInPackage(OptionalProductOrder optionalProductOrder) throws OptionalProductOrderException {
        if (optionalProductOrderService.getOptionalProductOrderByOptionalProductIdAndOrderId(optionalProductOrder.getOptional().getId(), optionalProductOrder.getOrder().getId()) == null) {
            optionalProductOrderService.createOptionalProductOrder(optionalProductOrder);
        } else {
            throw new OptionalProductOrderAlreadyExistingException();
        }
    }
}
