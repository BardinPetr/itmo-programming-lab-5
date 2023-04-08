package ru.bardinpetr.itmo.lab5.network.app.models;

/**
 * @param <K> response payload
 */
public record AppResponse<K>(ResponseStatus status, K payload) {

    public enum ResponseStatus {
        OK, CLIENT_ERROR, SERVER_ERROR
    }
}
