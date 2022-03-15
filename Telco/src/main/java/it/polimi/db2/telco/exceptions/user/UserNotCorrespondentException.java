package it.polimi.db2.telco.exceptions.user;

public class UserNotCorrespondentException extends UserException {
    public UserNotCorrespondentException() {
        super("The username is not the correspondent username of the user searched by email.");
    }
}