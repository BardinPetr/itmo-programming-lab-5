package ru.bardinpetr.itmo.lab5.network.server;

/**
 * Interface for interacting with low-level socket server/client
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
public interface IChannelController<K> extends IChannelReceiver<K>, IChannelSender<K> {
}
