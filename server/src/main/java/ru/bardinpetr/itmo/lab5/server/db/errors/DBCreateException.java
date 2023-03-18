package ru.bardinpetr.itmo.lab5.server.db.errors;

/**
 * Thrown when DB can't be created or accessed on start
 */
public class DBCreateException extends Exception {
    public DBCreateException(Exception e) {
        super("Could not set up database", e);
    }
}
