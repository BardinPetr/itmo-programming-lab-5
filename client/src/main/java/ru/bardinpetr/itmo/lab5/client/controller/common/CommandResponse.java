package ru.bardinpetr.itmo.lab5.client.controller.common;

import ru.bardinpetr.itmo.lab5.models.commands.base.responses.ICommandResponse;

/**
 * Response class on local command execution
 *
 * @param isSuccess if request was successful
 * @param message   textual message
 * @param payload   ICommandResponse-based payload or response
 */
public record CommandResponse(boolean isSuccess, String message, ICommandResponse payload) {
    public static CommandResponse ok() {
        return new CommandResponse(true, null, null);
    }

    public static CommandResponse error(String msg) {
        return new CommandResponse(false, msg, null);
    }
}
