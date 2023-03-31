package ru.bardinpetr.itmo.lab5.network.server.errors;

public class SendException extends RuntimeException {
    public SendException(Exception e) {
        super("Exception during sending: %s".formatted(e.getMessage()));
    }

    public SendException(String e) {
        super("Exception during sending: %s".formatted(e));
    }
}
