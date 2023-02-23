package ru.bardinpetr.itmo.lab5.client.tui.commands.controller.exceptions;


/**
 * Exception representing situation when script file can't be executed.
 */
public class ScriptExecuteException extends RuntimeException {
    public ScriptExecuteException(String s) {
        super(s);
    }
}
