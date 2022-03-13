package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.services.OptionalProductService;

import javax.inject.Inject;

public class OptionalProductController {
    @Inject
    OptionalProductService optionalProductService;

    public OptionalProductController(){}

    public OptionalProduct getOptionalProductById(Integer optionalProductId) {
        return optionalProductService.getOptionalProductById(optionalProductId);
    }

    public OptionalProduct getOptionalProductByName(String name) {
        return optionalProductService.getOptionalProductsByName(name);
    }

    public Integer createOptionalProduct(OptionalProduct optionalProduct) throws Exception {
        Integer optionalProductId;
        if (optionalProductService.getOptionalProductsByName(optionalProduct.getName()) == null) {
            optionalProductId = optionalProductService.createOptionalProduct(optionalProduct);
        } else {
            throw new Exception("");
        }
        return optionalProductId;
    }

    public Integer updateOptionalProduct(OptionalProduct optionalProduct) throws Exception {
        Integer optionalProductId;
        if (optionalProductService.getOptionalProductById(optionalProduct.getId()) != null) {
            optionalProductId = optionalProductService.createOptionalProduct(optionalProduct);
        } else {
            throw new Exception("");
        }
        return optionalProductId;
    }

    public void deleteOptionalProduct(Integer optionalProductId) throws Exception {
        OptionalProduct optionalProduct = optionalProductService.getOptionalProductById(optionalProductId);
        if (optionalProduct != null) {
            optionalProductService.deleteOptionalProduct(optionalProduct);
        } else {
            throw new Exception("");
        }
    }
}
