package ru.bardinpetr.itmo.lab5.network.session;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.IChannelSender;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for handling message sending queue with acknowledgements and retransmits on timeout
 */
public class SessionSendController<K> {
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
     * @param session session for current sender
     * @param message original message
     */
    public void onReceivedACK(Session<K> session, SocketMessage message) {
        boolean isOk = message.getCmdType() == SocketMessage.CommandType.ACK;
        var request = pendingMessageBuffer.get(null); // TODO
        if (request == null)
            return;

        request.callback().onReceive(isOk, null);
    }

    /**
     * Method for processing DATA message with response on last write command.
     * Should be called from the receiving party
     *
     * @param session session for current sender
     * @param message original message
     */
    public <T> void onReceivedData(Session<K> session, SocketMessage message) {
    }


    /**
     * Send message and wait for it to be handled by recipient, who should send ACK/NACK.
     * After timeout without ack received, the message is considered lost and resent.
     * If replying party sends ACK command with "waitData" flag set, waits for DATA message with response,
     * otherwise returns at ACK/NACK.
     *
     * @param session   session to which send message
     * @param message   message to send
     * @param onReceive handler which will be called if ACK/NACK received or when response DATA arrived
     * @param <R>       response payload type
     */
    public <T, R> void send(Session<K> session, SocketMessage message, SocketMessageHandler<R> onReceive) {
        var target = session.getAddress();
        controller.write(target, message);

        var msg = new PendingMessage<>(
                target,
                message,
                (SocketMessageHandler<R>) (isOk, payload) -> {
                    // TODO check if need to wait for DATA packet
                    unscheduleResend(target);
                    onReceive.onReceive(isOk, payload);
                }
        );

//        pendingMessageBuffer.put(, msg);
        scheduleResend(msg);
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
    private void unscheduleResend(K target) {
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
