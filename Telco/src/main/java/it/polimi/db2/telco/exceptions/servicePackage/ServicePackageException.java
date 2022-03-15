package it.polimi.db2.telco.exceptions.servicePackage;

public class ServicePackageException extends RuntimeException {
    public ServicePackageException(String message) {
        super(message);
    }

    public ServicePackageException(){ super();}
}
