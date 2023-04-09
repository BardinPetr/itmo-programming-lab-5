package ru.bardinpetr.itmo.lab5.network.app.models;

import lombok.Data;

/**
 * @param <K> response payload
 */
@Data
public class AppResponse<K> {

    private ResponseStatus status = ResponseStatus.WAITING;
    private K payload = null;

    public AppResponse(ResponseStatus status, K payload) {
        this.status = status;
        this.payload = payload;
    }

    public AppResponse() {
    }

    public enum ResponseStatus {
        OK, CLIENT_ERROR, SERVER_ERROR, WAITING
    }
}
