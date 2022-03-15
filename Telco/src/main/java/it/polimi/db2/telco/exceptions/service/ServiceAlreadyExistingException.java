package it.polimi.db2.telco.exceptions.service;

public class ServiceAlreadyExistingException extends ServiceException {
    public ServiceAlreadyExistingException () {super("Service already existing.");}
}
