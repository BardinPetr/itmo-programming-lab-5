package ru.bardinpetr.itmo.lab5.network.session;

import ru.bardinpetr.itmo.lab5.network.models.BaseMessage;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.interfaces.IChannelSender;
import ru.bardinpetr.itmo.lab5.network.session.handlers.SocketMessageHandler;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for handling message sending queue with acknowledgements and retransmits on timeout
 * Message order should be enforced before calling this module
 */
@Deprecated(forRemoval = true)
public class SessionSendController<K> {
    // Maps pending outgoing message to its ID => response will have replyId set to getReplyCounter(request)
    private final Map<Long, PendingMessage<K, ?>> pendingMessageBuffer = new HashMap<>();
    private final IChannelSender<K> controller;

    /**
     * @param controller interface for sending messages back to the channel
     */
    public SessionSendController(IChannelSender<K> controller) {
        this.controller = controller;
    }

    /**
     * Method for processing ACK/NACK for message.
     * Should be called from the receiving party
     *
     * @param message original message
     */
    public void onReceivedACK(SocketMessage message) {
        var replyOn = message.getReplyId();
        var request = pendingMessageBuffer.get(replyOn);
        if (request == null) {
            // TODO: handle invalid
            return;
        }

        var isOk = message.getCmdType() == SocketMessage.CommandType.ACK;
        if (!message.isContinued()) {
            var callback = request.callback();
            if (callback != null) callback.onReceive(isOk, message.getPayload());

            finalizeRequest(replyOn);
        }
    }

    /**
     * Method for processing DATA message with response on last write command.
     * Should be called from the receiving party
     *
     * @param message original message
     */
    public void onReceivedDataResponse(SocketMessage message) {
        var replyOn = message.getReplyId();
        var request = pendingMessageBuffer.get(replyOn);
        if (request == null) return;

        var callback = request.callback();
        if (callback != null) callback.onReceive(true, message.getPayload());

        finalizeRequest(replyOn);
    }

    /**
     * Called when processing of request ended (received either ACK of ACK+DATA)
     *
     * @param requestId id of original request
     */
    private void finalizeRequest(Long requestId) {
        var request = pendingMessageBuffer.remove(requestId);
        if (request == null) return;
        unscheduleResend(requestId);
    }


    /**
     * Send message and wait for it to be handled by recipient, who should send ACK/NACK.
     * After timeout without ack received, the message is considered lost and resent.
     * If replying party sends ACK command with "continued" flag set, waits for DATA message with response,
     * otherwise returns at ACK/NACK.
     *
     * @param session   session to which send message
     * @param message   message to send
     * @param onReceive handler which will be called if ACK/NACK received or when response DATA arrived
     * @param <R>       response payload type
     */
    public <R> void send(Session<K> session, BaseMessage message, boolean isContinued, SocketMessageHandler<R> onReceive) {
        var target = session.getAddress();

        var preparedMessage = session.prepareSend(message);
        preparedMessage.setContinued(isContinued);

        var msg = new PendingMessage<>(
                target,
                preparedMessage,
                onReceive
        );

        pendingMessageBuffer.put(preparedMessage.getId(), msg);
        scheduleResend(msg);

        controller.write(target, preparedMessage);
    }

    public <R> void sendResponse(Session<K> session, BaseMessage message, SocketMessageHandler<R> onReceive) {
        send(session, message, true, onReceive);
    }

    public <R> void send(Session<K> session, BaseMessage message, SocketMessageHandler<R> onReceive) {
        send(session, message, false, onReceive);
    }

    /**
     * Sets timeout timer for ACK waiting
     */
    private <R> void scheduleResend(PendingMessage<K, R> msg) {
        // TODO
    }

    /**
     * Removes timeout timer for ACK waiting
     */
    private void unscheduleResend(Long targetMessage) {
        // TODO
    }

    /**
     * Should be called externally when identified duplicated request.
     *
     * @param curSession
     * @param message
     */
    public void handleDuplicateRequest(Session<K> curSession, SocketMessage message) {
        // TODO
    }

    /**
     * @param target   recipient (from whom to wait response)
     * @param message  message to be sent
     * @param callback response callback function
     * @param <K>      recipient address type
     * @param <R>      response payload type
     */
    private record PendingMessage<K, R>(K target, SocketMessage message, SocketMessageHandler<R> callback) {
    }
}
