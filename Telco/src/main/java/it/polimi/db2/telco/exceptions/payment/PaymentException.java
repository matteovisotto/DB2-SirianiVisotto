package it.polimi.db2.telco.exceptions.payment;

public class PaymentException extends RuntimeException {
    public PaymentException(String message) {
        super(message);
    }

    public PaymentException(){ super();}
}
