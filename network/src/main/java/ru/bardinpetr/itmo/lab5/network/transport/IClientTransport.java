package ru.bardinpetr.itmo.lab5.network.transport;


import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

/**
 * Interface for client transport protocols implementations
 *
 * @param <T> base message
 */
public interface IClientTransport<T> {

    /**
     * Synchronously send request.
     */
    void send(T data);

    /**
     * Synchronously wait for incoming message
     *
     * @param timeout timeout or null if no timeout should be applied
     * @return message or TimeoutException is thrown
     * @throws TimeoutException if no message arrived before timeout
     * @throws IOException      if channel is not
     */
    T receive(Duration timeout) throws TimeoutException, IOException;
}
