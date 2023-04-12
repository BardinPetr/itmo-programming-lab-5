package ru.bardinpetr.itmo.lab5.network.server.handlers;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;


/**
 * Function for handling message
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
public interface IMessageHandler<K> {
    void handle(K sender, SocketMessage message);
}
