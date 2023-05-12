package ru.bardinpetr.itmo.lab5.server.dao.sync;

public class NotOwnedException extends RuntimeException {
    public NotOwnedException(String user) {
        super("Object is not owned by user %s".formatted(user));
    }
}
