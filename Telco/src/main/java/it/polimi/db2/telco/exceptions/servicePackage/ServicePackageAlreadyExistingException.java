package it.polimi.db2.telco.exceptions.servicePackage;

public class ServicePackageAlreadyExistingException extends ServicePackageException {
    public ServicePackageAlreadyExistingException() {super("Service package already existing.");}
}
