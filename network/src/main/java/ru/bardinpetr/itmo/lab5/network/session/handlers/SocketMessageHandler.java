package ru.bardinpetr.itmo.lab5.network.session.handlers;

/**
 * Interface for
 *
 * @param <T> type of response
 */
@Deprecated(forRemoval = true)
public interface SocketMessageHandler<T> {
    void onReceive(boolean isOk, Object payload);
}
