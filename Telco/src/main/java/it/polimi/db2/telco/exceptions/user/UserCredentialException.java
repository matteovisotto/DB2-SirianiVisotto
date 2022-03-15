package it.polimi.db2.telco.exceptions.user;

public class UserCredentialException extends UserException {
    public UserCredentialException() {
        super("Wrong username/email or password.");
    }
}
