package it.polimi.db2.telco.exceptions.administrator;

public class AdministratorCredentialException extends AdministratorException {
    public AdministratorCredentialException () { super("Wrong email or password.");}
}
