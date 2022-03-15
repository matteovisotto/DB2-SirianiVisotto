package it.polimi.db2.telco.exceptions.order;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }

    public OrderException(){ super();}
}
