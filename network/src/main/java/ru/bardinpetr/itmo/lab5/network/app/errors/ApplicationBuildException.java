package ru.bardinpetr.itmo.lab5.network.app.errors;

public class ApplicationBuildException extends RuntimeException {
    public ApplicationBuildException(String s) {
        super(s);
    }
}
