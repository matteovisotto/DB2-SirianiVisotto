package it.polimi.db2.telco.exceptions.order;

public class OrderAlreadyPayedException extends OrderException {
    public OrderAlreadyPayedException() {super("Order already payed.");}
}
