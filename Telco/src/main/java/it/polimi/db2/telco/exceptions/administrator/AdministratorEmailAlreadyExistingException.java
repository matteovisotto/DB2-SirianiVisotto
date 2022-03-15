package it.polimi.db2.telco.exceptions.administrator;

public class AdministratorEmailAlreadyExistingException extends AdministratorException {
    public AdministratorEmailAlreadyExistingException () { super("Administrator email already existing.");}
}
