package ru.bardinpetr.itmo.lab5.network.server.interfaces;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

/**
 * Interface for channel controller to send data
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
public interface IChannelSender<K> {

    /**
     * Send to message to socket. Do not deal with any resends and N-/ACKs
     *
     * @param address address to whom to send
     * @param message message to send
     */
    void write(K address, SocketMessage message);
}
