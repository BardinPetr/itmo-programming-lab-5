package ru.bardinpetr.itmo.lab5.client.parser.error;

/**
 * Expression in parsing commans
 */
public class ParserException extends Exception {
    public ParserException(String msg) {
        super(msg);
    }
}
