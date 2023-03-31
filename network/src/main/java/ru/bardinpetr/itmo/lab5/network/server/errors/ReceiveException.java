package ru.bardinpetr.itmo.lab5.network.server.errors;

public class ReceiveException extends RuntimeException {
    public ReceiveException(Exception e) {
        super("Exception during receiving: %s".formatted(e.getMessage()));
    }

    public ReceiveException(String e) {
        super("Exception during receiving: %s".formatted(e));
    }
}
