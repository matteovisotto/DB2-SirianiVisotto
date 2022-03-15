package it.polimi.db2.telco.exceptions.administrator;

public class AdministratorNotFoundException extends AdministratorException {
    public AdministratorNotFoundException () { super("Administrator not found.");}
}
