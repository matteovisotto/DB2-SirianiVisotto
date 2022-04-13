package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.InsolventUser;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.services.InsolventUserService;

import javax.inject.Inject;
import java.util.List;

public class InsolventUserController {
    @Inject
    InsolventUserService insolventUserService;

    public InsolventUserController(){}

    /*public InsolventUser getInsolventUserById(Integer insolventUserId) throws UserException {
        return insolventUserService.getInsolventUserById(insolventUserId);
    }

    public InsolventUser getInsolventUserByEmail(String mail) throws UserException {
        return insolventUserService.getInsolventUserByEmail(mail);
    }

    public InsolventUser getInsolventUserByUsername(String username) throws UserException {
        return insolventUserService.getInsolventUserByUsername(username);
    }*/

    public List<InsolventUser> getAllInsolventUsers() {
        return insolventUserService.getAllInsolventUsers();
    }
}
