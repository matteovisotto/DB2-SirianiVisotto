package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.TotValueOptionalNoOptional;
import it.polimi.db2.telco.services.TotValueOptionalNoOptionalService;

import javax.inject.Inject;
import java.util.List;

public class TotValueOptionalNoOptionalController {
    @Inject
    TotValueOptionalNoOptionalService totValueOptionalNoOptionalService;

    public List<TotValueOptionalNoOptional> getAllTotValueOptionalNoOptional() {
        return totValueOptionalNoOptionalService.getAllTotValueOptionalNoOptional();
    }

}
