package it.polimi.db2.telco.exceptions.payment;

public class PaymentNotFoundException extends PaymentException {
    public PaymentNotFoundException() {super("Service package not found.");}
}
