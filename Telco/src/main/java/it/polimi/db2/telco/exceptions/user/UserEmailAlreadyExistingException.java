package it.polimi.db2.telco.exceptions.user;

public class UserEmailAlreadyExistingException extends UserException {
    public UserEmailAlreadyExistingException() {
        super("User email already existing.");
    }
}
