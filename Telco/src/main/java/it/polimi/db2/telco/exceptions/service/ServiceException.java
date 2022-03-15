package it.polimi.db2.telco.exceptions.service;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(){ super();}
}
