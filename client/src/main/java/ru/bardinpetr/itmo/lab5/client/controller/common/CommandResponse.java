package ru.bardinpetr.itmo.lab5.client.controller.common;

/**
 * Response class on local command execution
 *
 * @param isSuccess if request was successful
 * @param message   textual message
 * @param payload   payload or response
 * @param <T>       response payload type
 */
public record CommandResponse<T>(boolean isSuccess, String message, T payload) {
    public static <T> CommandResponse<T> ok() {
        return new CommandResponse<>(true, null, null);
    }

    public static <T> CommandResponse<T> error(String msg) {
        return new CommandResponse<>(false, msg, null);
    }
}
