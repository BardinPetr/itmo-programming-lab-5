package ru.bardinpetr.itmo.lab5.network.transport.errors;

public class TransportTimeoutException extends Exception {
    public TransportTimeoutException(String msg) {
        super(msg);
    }

    public TransportTimeoutException() {
        super("TransportTimeoutException.error");
    }
}
