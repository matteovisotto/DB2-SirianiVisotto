package it.polimi.db2.telco.exceptions;

public class UserNotFoundException extends UserException {
    public UserNotFoundException() {
        super("User not found");
    }
}
