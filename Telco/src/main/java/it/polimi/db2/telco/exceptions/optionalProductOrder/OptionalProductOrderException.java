package it.polimi.db2.telco.exceptions.optionalProductOrder;

public class OptionalProductOrderException extends RuntimeException {
    public OptionalProductOrderException(String message) {
        super(message);
    }

    public OptionalProductOrderException(){ super();}
}
