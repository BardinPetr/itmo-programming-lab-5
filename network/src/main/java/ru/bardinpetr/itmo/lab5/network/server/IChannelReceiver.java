package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;

/**
 * Interface for channel controller to receive data
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
public interface IChannelReceiver<K> {
    /**
     * Subscribe on all messages arrives
     *
     * @param handler function to be called message arrives
     */
    void subscribe(IMessageHandler<K> handler);

    /**
     * Subscribe on specific messages arrive
     *
     * @param handler function to be called message arrives
     */
    void subscribe(IMessageHandler<K> handler, SocketMessage.CommandType... types);

}
