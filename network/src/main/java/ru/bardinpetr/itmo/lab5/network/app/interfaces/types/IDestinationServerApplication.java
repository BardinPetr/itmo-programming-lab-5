package ru.bardinpetr.itmo.lab5.network.app.interfaces.types;

import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

/**
 * Interface for transport controller to send AppResponses to channel
 *
 * @param <U> user identifier
 * @param <T> message type
 */
public interface IDestinationServerApplication<U, T> {
    void send(AppResponse<U, T> response);
}
