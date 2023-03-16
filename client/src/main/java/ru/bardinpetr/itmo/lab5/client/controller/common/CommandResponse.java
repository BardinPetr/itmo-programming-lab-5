package ru.bardinpetr.itmo.lab5.client.controller.common;

public record CommandResponse(boolean isSuccess, String message) {
    public static CommandResponse ok() {
        return new CommandResponse(true, "OK");
    }

    public static CommandResponse error(String msg) {
        return new CommandResponse(false, msg);
    }
}
