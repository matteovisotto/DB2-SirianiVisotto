package it.polimi.db2.telco.exceptions.user;

public class UserUsernameAlreadyExistingException extends UserException {
    public UserUsernameAlreadyExistingException() {
        super("User username already existing.");
    }
}