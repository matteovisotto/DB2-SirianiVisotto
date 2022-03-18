package it.polimi.db2.telco.exceptions.materialized;

public class MaterializedException extends RuntimeException {
    public MaterializedException(String message) {
        super(message);
    }

    public MaterializedException(){ super();}
}
