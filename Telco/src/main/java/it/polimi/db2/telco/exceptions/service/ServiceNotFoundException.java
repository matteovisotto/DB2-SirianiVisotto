package it.polimi.db2.telco.exceptions.service;

public class ServiceNotFoundException extends ServiceException {
    public ServiceNotFoundException() {super("Service not found.");}
}
