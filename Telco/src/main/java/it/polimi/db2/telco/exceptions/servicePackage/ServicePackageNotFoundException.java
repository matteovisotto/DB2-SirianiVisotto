package it.polimi.db2.telco.exceptions.servicePackage;

public class ServicePackageNotFoundException extends ServicePackageException {
    public ServicePackageNotFoundException() {super("Service package not found.");}
}
