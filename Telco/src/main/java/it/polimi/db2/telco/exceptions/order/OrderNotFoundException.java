package it.polimi.db2.telco.exceptions.order;

public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException() {super("Order not found.");}
}
