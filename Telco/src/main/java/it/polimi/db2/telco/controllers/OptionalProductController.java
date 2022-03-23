package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductAlreadyExistingException;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductException;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductNotFoundException;
import it.polimi.db2.telco.services.OptionalProductService;

import javax.inject.Inject;
import java.util.List;

public class OptionalProductController {
    @Inject
    OptionalProductService optionalProductService;

    public OptionalProductController(){}

    public OptionalProduct getOptionalProductById(Integer optionalProductId) throws OptionalProductException {
        return optionalProductService.getOptionalProductById(optionalProductId);
    }

    public OptionalProduct getOptionalProductByName(String name) throws OptionalProductException {
        return optionalProductService.getOptionalProductByName(name);
    }

    public List<OptionalProduct> getAllOptionalProducts() throws OptionalProductException {
        return optionalProductService.getAllOptionalProducts();
    }

    public Integer createOptionalProduct(OptionalProduct optionalProduct) throws OptionalProductException {
        Integer optionalProductId;
        if (!optionalProductService.isOptionalProductAlreadyExisting(optionalProduct.getName())) {
            optionalProductId = optionalProductService.createOptionalProduct(optionalProduct);
        } else {
            throw new OptionalProductAlreadyExistingException();
        }
        return optionalProductId;
    }

    public Integer updateOptionalProduct(OptionalProduct optionalProduct) throws OptionalProductException {
        Integer optionalProductId;
        if (optionalProductService.getOptionalProductById(optionalProduct.getId()) != null) {
            optionalProductId = optionalProductService.updateOptionalProduct(optionalProduct);
        } else {
            throw new OptionalProductNotFoundException();
        }
        return optionalProductId;
    }

    public void deleteOptionalProduct(Integer optionalProductId) throws OptionalProductException {
        OptionalProduct optionalProduct = optionalProductService.getOptionalProductById(optionalProductId);
        if (optionalProduct != null) {
            optionalProductService.deleteOptionalProduct(optionalProduct);
        } else {
            throw new OptionalProductNotFoundException();
        }
    }
}
