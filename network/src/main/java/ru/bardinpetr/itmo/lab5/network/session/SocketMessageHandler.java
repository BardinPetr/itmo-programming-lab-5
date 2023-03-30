package ru.bardinpetr.itmo.lab5.network.session;

/**
 * Interface for
 *
 * @param <T> type of response
 */
public interface SocketMessageHandler<T> {
    void onReceive(boolean isOk, Object payload);
}
