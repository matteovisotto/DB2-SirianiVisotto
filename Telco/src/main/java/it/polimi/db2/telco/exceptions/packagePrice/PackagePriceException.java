package it.polimi.db2.telco.exceptions.packagePrice;

public class PackagePriceException extends RuntimeException {
    public PackagePriceException(String message) {
        super(message);
    }

    public PackagePriceException(){ super();}
}
