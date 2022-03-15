package it.polimi.db2.telco.exceptions.administrator;

public class AdministratorException extends RuntimeException {
    public AdministratorException(String message) {
        super(message);
    }

    public AdministratorException(){ super();}
}
