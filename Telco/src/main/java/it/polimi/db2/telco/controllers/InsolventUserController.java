package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.InsolventUser;
import it.polimi.db2.telco.services.InsolventUserService;

import javax.inject.Inject;
import java.util.List;

public class InsolventUserController {
    @Inject
    InsolventUserService insolventUserService;

    public InsolventUserController(){}

    public List<InsolventUser> getAllInsolventUsers() {
        return insolventUserService.getAllInsolventUsers();
    }
}
