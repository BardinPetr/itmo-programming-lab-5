package ru.bardinpetr.itmo.lab5.client.ui.cli.utils.errors;

public class ScriptRecursionRootException extends RuntimeException {
    public ScriptRecursionRootException(String message) {
        super(message);
    }
}
