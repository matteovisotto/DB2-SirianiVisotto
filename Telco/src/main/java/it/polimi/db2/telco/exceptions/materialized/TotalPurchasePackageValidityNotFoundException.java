package it.polimi.db2.telco.exceptions.materialized;

public class TotalPurchasePackageValidityNotFoundException extends MaterializedException {
    public TotalPurchasePackageValidityNotFoundException() {super("Element of total purchase package validity not found.");}
}