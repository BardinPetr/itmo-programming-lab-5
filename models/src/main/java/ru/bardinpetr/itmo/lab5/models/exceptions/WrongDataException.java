package ru.bardinpetr.itmo.lab5.models.exceptions;

public class WrongDataException extends Exception {

    private final String msg;

    public WrongDataException(String msg) {
        this.msg = msg;
    }

    public String getExceptionIngo() {
        return msg;
    }
}
