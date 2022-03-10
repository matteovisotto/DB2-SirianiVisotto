package it.polimi.db2.telco.exceptions;

public class UserCredentialException extends UserException {
    public UserCredentialException() {
        super("Wrong user credential");
    }
}
