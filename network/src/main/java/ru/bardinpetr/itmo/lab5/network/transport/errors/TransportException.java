package ru.bardinpetr.itmo.lab5.network.transport.errors;

public class TransportException extends Exception {
    public TransportException(String msg) {
        super(msg);
    }

    public TransportException(Throwable cause) {
        super(cause);
    }

    public TransportException() {
        super("Server is not available. Please resend later");
    }
}
