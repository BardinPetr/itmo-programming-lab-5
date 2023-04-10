package ru.bardinpetr.itmo.lab5.network.session.handlers;

import ru.bardinpetr.itmo.lab5.network.session.models.Session;

/**
 * Interface for server to request for data to send as reply for request
 */
@Deprecated(forRemoval = true)
public interface IMessageReplyHandler {

    /**
     * Execute commands based on payload and return response to be sent to requester
     *
     * @param data payload of message
     * @param <T>  request payload type
     * @return payload to respond
     */
    <T> byte[] run(Session<?> session, T data);
}
