package ru.bardinpetr.itmo.lab5.network.app.special;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.AbstractApplication;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.session.Session;
import ru.bardinpetr.itmo.lab5.network.framelevel.models.IIdentifiableMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;

/**
 * Represents Application capable of sending app-level messages to app-chain build from transport-level messages.
 * Message source (first application in chain) should implement this class.
 *
 * @param <Q> request base type
 * @param <A> response base type
 * @param <U> user identifier type
 * @param <L> low-level message object to be used as source for AppRequest
 */
@Slf4j
public abstract class AbstractInputTransportApplication<Q extends IIdentifiableCommand, A, U, L extends IIdentifiableMessage>
        extends AbstractApplication<Q, A> {

    private final IServerTransport<U, L> transport;

    public AbstractInputTransportApplication(IServerTransport<U, L> transport) {
        this.transport = transport;
        transport.subscribe(this::handle);
    }

    /**
     * Process in incoming message arrived from underlying protocol
     *
     * @param senderID        low-level user identifier
     * @param incomingMessage low-level message
     */
    private void handle(U senderID, L incomingMessage) {
        var status = AppRequest.ReqStatus.CREATED;

        log.info("New transport message arrived from {}", senderID);

        var msg = deserialize(incomingMessage);
        if (msg == null) {
            log.warn("Failed to deserialize message from {}", senderID);
            status = AppRequest.ReqStatus.INVALID;
        }

        var session = supplySession(senderID, incomingMessage);

        var appRequest = new AppRequest<Q, A>(
                status,
                incomingMessage.getId(),
                session,
                null,
                msg
        );

        process(appRequest);
    }

    /**
     * Create session object for specific message from transport layer.
     * Called by handle() on new message arrived
     *
     * @param senderID        low-level user identifier
     * @param incomingMessage low-level message
     */
    protected abstract Session<U> supplySession(U senderID, L incomingMessage);

    /**
     * Deserialize incoming request (Q) from low level L type message object
     *
     * @param request low-level request
     * @return deserialized app-level response or null if invalid input
     */
    protected abstract Q deserialize(L request);

    @Override
    public boolean filter(AppRequest<Q, A> req) {
        return true;
    }
}
