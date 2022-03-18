package it.polimi.db2.telco.exceptions.materialized;

public class TotalPurchasePackageNotFoundException extends MaterializedException {
    public TotalPurchasePackageNotFoundException() {super("Element of total purchase package not found.");}
}