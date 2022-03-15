package it.polimi.db2.telco.exceptions.packagePrice;

public class PackagePriceNotFoundException extends PackagePriceException {
    public PackagePriceNotFoundException() {super("Payment not found.");}
}
