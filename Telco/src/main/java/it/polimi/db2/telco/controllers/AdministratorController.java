package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Administrator;
import it.polimi.db2.telco.exceptions.administrator.AdministratorCredentialException;
import it.polimi.db2.telco.exceptions.administrator.AdministratorException;
import it.polimi.db2.telco.services.AdministratorService;

import javax.inject.Inject;

public class AdministratorController {
    @Inject
    AdministratorService administratorService;

    public AdministratorController(){}

    public Administrator loginAdministrator(String email, String password) throws AdministratorException {
        Administrator administrator = administratorService.getAdministratorByEmail(email);
        if(administrator == null || !administrator.getPassword().equals(password)){
            throw new AdministratorCredentialException();
        }
        return administrator;
    }
}
