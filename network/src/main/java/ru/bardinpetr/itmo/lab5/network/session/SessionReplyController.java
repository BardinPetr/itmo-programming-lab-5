package ru.bardinpetr.itmo.lab5.network.session;

import ru.bardinpetr.itmo.lab5.network.models.BaseMessage;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.session.handlers.IMessageReplyHandler;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

/**
 * Service for handling input requests and sending responses.
 * Message order should be enforced before calling this module
 */
@Deprecated(forRemoval = true)
public class SessionReplyController<K> {
    private final SessionSendController<K> controller;
    private IMessageReplyHandler handler;

    /**
     * @param controller interface for sending messages back to the channel
     */
    public SessionReplyController(SessionSendController<K> controller) {
        this.controller = controller;
    }


    /**
     * Method for processing DATA message with incoming request.
     * Should be called from the receiving party
     *
     * @param message original message
     */
    public void onReceivedDataRequest(Session<K> session, SocketMessage message) {
        if (handler == null) {
            controller.send(session, BaseMessage.NACK(), null);
            return;
        }

        controller.send(session, BaseMessage.ACK(), true, null);

        var response = handler.run(session, message.getPayload());
        controller.send(
                session,
                new BaseMessage(SocketMessage.CommandType.DATA, response),
                null
        );
    }

    /**
     * Set handler for incoming commands
     *
     * @param handler function (synchronous) to return reply
     */
    public void setHandler(IMessageReplyHandler handler) {
        this.handler = handler;
    }
}
