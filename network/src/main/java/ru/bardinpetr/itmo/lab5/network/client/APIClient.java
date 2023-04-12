package ru.bardinpetr.itmo.lab5.network.client;

import lombok.Setter;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.client.error.APIClientException;
import ru.bardinpetr.itmo.lab5.network.framelevel.IClientTransport;
import ru.bardinpetr.itmo.lab5.network.framelevel.IIdentifiableMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

/**
 * Base class for implementing api clients
 *
 * @param <T> Low level message type
 */
public abstract class APIClient<T extends IIdentifiableMessage> {
    private final IClientTransport<T> transport;
    private Long currentMessageId = 0L;

    @Setter
    private Duration timeout = null;

    public APIClient(IClientTransport<T> transport) {
        this.transport = transport;
    }

    public APICommandResponse request(APICommand request) throws TimeoutException, APIClientException {
        var message = serialize(request);
        if (message == null)
            throw new APIClientException("Failed to serialize");

        message.setId(currentMessageId++);

        transport.send(message);

        T reply;
        try {
            reply = transport.receive(timeout);
        } catch (IOException e) {
            throw new APIClientException(e);
        }

        if (!validateReply(reply)) {
            throw new APIClientException("Not valid id");
        }

        var deserialized = deserialize(reply);
        if (deserialized == null)
            throw new APIClientException("Failed to deserialize");

        return deserialized;
    }

    private boolean validateReply(T reply) {
        return true;
    }

    /**
     * Serialize APICommand into low level T type message object
     *
     * @param request
     * @return
     */
    abstract protected T serialize(APICommand request);

    abstract protected APICommandResponse deserialize(T request);
}