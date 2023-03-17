package ru.bardinpetr.itmo.lab5.client.controller.common;

import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;

public record CommandResponse(boolean isSuccess, String message, ICommandResponse payload) {
    public static CommandResponse ok() {
        return new CommandResponse(true, "OK", null);
    }

    public static CommandResponse error(String msg) {
        return new CommandResponse(false, msg, null);
    }
}
