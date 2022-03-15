package it.polimi.db2.telco.exceptions.activation;

public class ActivationNotFoundException extends ActivationException {
    public ActivationNotFoundException() {super("Activation not found.");}
}
