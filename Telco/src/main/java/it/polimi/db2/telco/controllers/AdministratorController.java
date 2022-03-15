package it.polimi.db2.telco.controllers;

import it.polimi.db2.telco.entities.Administrator;
import it.polimi.db2.telco.exceptions.administrator.AdministratorCredentialException;
import it.polimi.db2.telco.exceptions.administrator.AdministratorEmailAlreadyExistingException;
import it.polimi.db2.telco.exceptions.administrator.AdministratorException;
import it.polimi.db2.telco.exceptions.administrator.AdministratorNotFoundException;
import it.polimi.db2.telco.services.AdministratorService;

import javax.inject.Inject;
import java.util.List;

public class AdministratorController {
    @Inject
    AdministratorService administratorService;

    public AdministratorController(){}

    public Administrator loginAdministrator(String email, String password) throws AdministratorException {
        Administrator administrator = administratorService.getAdministratorByEmail(email);
        if(!administrator.getPassword().equals(password)){
            throw new AdministratorCredentialException();
        }
        return administrator;
    }

    public Administrator getAdministratorById(Integer administratorId) throws AdministratorException {
        return administratorService.getAdministratorById(administratorId);
    }

    public List<Administrator> getAdministratorsByRole(String role) throws AdministratorException {
        return administratorService.getAdministratorsByRole(role);
    }

    public Integer createAdministrator(Administrator administrator) throws AdministratorException {
        Integer administratorId;
        if (administratorService.getAdministratorByEmail(administrator.getEmail()) == null) {
            //Hash the password
            administratorId = administratorService.createAdministrator(administrator);
        } else {
            throw new AdministratorEmailAlreadyExistingException();
        }
        return administratorId;
    }

    public Integer updateAdministrator(Administrator administrator) throws AdministratorException {
        Integer administratorId;
        if (administratorService.getAdministratorByEmail(administrator.getEmail()) != null) {
            //Hash the password
            administratorId = administratorService.updateAdministrator(administrator);
        } else {
            throw new AdministratorNotFoundException();
        }
        return administratorId;
    }

    public void deleteAdministrator(Integer administratorId) throws AdministratorException {
        Administrator administrator = administratorService.getAdministratorById(administratorId);
        if (administrator != null) {
            administratorService.deleteAdministrator(administrator);
        } else {
            throw new AdministratorNotFoundException();
        }
    }
}
