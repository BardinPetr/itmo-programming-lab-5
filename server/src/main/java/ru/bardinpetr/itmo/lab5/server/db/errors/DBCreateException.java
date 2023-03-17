package ru.bardinpetr.itmo.lab5.server.db.errors;

public class DBCreateException extends Exception {
    public DBCreateException(Exception e) {
        super("Could not set up database", e);
    }
}
