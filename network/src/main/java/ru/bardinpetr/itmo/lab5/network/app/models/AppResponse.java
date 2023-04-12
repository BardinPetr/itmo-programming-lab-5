package ru.bardinpetr.itmo.lab5.network.app.models;

import lombok.Data;

/**
 * @param <U> recipient user identifier
 * @param <T> response payload
 */
@Data
public class AppResponse<U, T> {

    private ResponseStatus status = ResponseStatus.WAITING;
    private U recipient = null;
    private T payload = null;

    public AppResponse(U recipient) {
        this.recipient = recipient;
    }

    public enum ResponseStatus {
        OK, ERROR, WAITING
    }
}
