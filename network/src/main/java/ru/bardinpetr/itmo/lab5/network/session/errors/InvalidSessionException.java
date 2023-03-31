package ru.bardinpetr.itmo.lab5.network.session.errors;

public class InvalidSessionException extends RuntimeException {
    public InvalidSessionException(String description) {
        super("Invalid session passed%s".formatted(description));
    }
}
