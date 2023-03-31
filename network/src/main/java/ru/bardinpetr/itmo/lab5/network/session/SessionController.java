package ru.bardinpetr.itmo.lab5.network.session;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.models.BaseMessage;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.server.interfaces.IChannelController;
import ru.bardinpetr.itmo.lab5.network.session.errors.InvalidSessionException;
import ru.bardinpetr.itmo.lab5.network.session.handlers.ISessionMessageHandler;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling sessions for UDP
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
@Slf4j
public class SessionController<K> {

    private final Map<K, Session<K>> sessions = new HashMap<>();
    private final IChannelController<K> controller;
    private final SessionSendController<K> sendController;
    private final SessionReplyController<K> replyController;


    public SessionController(IChannelController<K> controller) {
        this.controller = controller;

        controller.subscribe(loadSessionAdapter(this::handleACKCmd), SocketMessage.CommandType.ACK, SocketMessage.CommandType.NACK);
        controller.subscribe(this::handleSessionInitCmd, SocketMessage.CommandType.INIT);
        controller.subscribe(this::handleSessionHaltCmd, SocketMessage.CommandType.HALT);
        controller.subscribe(loadSessionAdapter(this::handleDataCmd), SocketMessage.CommandType.DATA);

        this.sendController = new SessionSendController<>(controller);
        this.replyController = new SessionReplyController<>(sendController);
    }

    /**
     * Start handshake process (for external use)
     *
     * @param address target address
     */
    public void beginSession(K address) {
        if (sessions.containsKey(address)) {
            throw new InvalidSessionException("Session already existed");
        }

        var session = new Session<>(address);
        sessions.put(address, session);

        sendController.send(session, new BaseMessage(SocketMessage.CommandType.INIT, null), (success, payload) -> sessions.get(address).setState(Session.State.WAITING_HANDSHAKE));
    }

    /**
     * End session (for external use)
     *
     * @param address
     */
    public void closeSession(K address) {
        var session = sessions.get(address);

        if (session == null) {
            throw new InvalidSessionException("Session already existed");
        }

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.HALT, null),
                (success, payload) -> session.setState(Session.State.DEAD)
        );
    }

    /**
     * Called when other party offered INIT
     * (both when we are the initiator and when not)
     *
     * @param address incoming address
     * @param message INIT message
     */
    private void handleSessionInitCmd(K address, SocketMessage message) {
        var existedSession = sessions.get(address);

        if (existedSession != null) {
            if (existedSession.getState() == Session.State.OPERATING)
                controller.write(address, new SocketMessage(SocketMessage.CommandType.NACK, 0L, message.getId()));
            else if (existedSession.getState() == Session.State.WAITING_HANDSHAKE)
                acceptLastHandshake(existedSession, message);
            return;
        }

        var session = new Session<>(address);
        session.registerIncoming(message);
        sessions.put(address, session);

        sendController.send(session, BaseMessage.ACK(), null);

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.INIT, null),
                (success, payload) -> sessions.get(address).setState(Session.State.OPERATING)
        );
    }

    /**
     * Called when this party initialized handshake, counterpart sent ACK on our INIT and offered its INIT
     *
     * @param session session in state of WAITING_HANDSHAKE
     * @param message INIT message of opponent
     */
    private void acceptLastHandshake(Session<K> session, SocketMessage message) {
        session.setState(Session.State.OPERATING);
        session.registerIncoming(message);
        sendController.send(session, BaseMessage.ACK(), null);
    }

    /**
     * Close session
     *
     * @param address opponent address
     * @param message HALT message
     */
    private void handleSessionHaltCmd(K address, SocketMessage message) {
        var session = sessions.get(address);

        if (session == null) {
            controller.write(address, new SocketMessage(SocketMessage.CommandType.ACK, 0L, message.getId()));
            return;
        }

        if (session.getState() == Session.State.DEAD) {
            // already done de-initializing process and here having last message processed
            sessions.remove(address);
            return;
        }

        sendController.send(session, BaseMessage.ACK(), null);
        session.setState(Session.State.DEAD);

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.HALT, null),
                (success, payload) -> sessions.remove(address)
        );
    }

    private void handleDataCmd(Session<K> session, SocketMessage message) {
        if (message.isResponse()) {
            sendController.onReceivedDataResponse(message);
        } else {
            replyController.onReceivedDataRequest(session, message);
        }
    }

    private void handleACKCmd(Session<K> session, SocketMessage message) {
        sendController.onReceivedACK(message);
    }

    private IMessageHandler<K> loadSessionAdapter(ISessionMessageHandler<K> inputEvent) {
        return (sender, message) -> {
            Session<K> curSession = sessions.get(sender);
            if (curSession != null) {
                var state = curSession.registerIncoming(message);

                if (state == Session.IncomingMessageState.DUPLICATE) {
                    sendController.handleDuplicateRequest(curSession, message);
                    return;
                } else if (state == Session.IncomingMessageState.EARLY) {
                    // TODO handle early messages
                }
            }

            inputEvent.handle(curSession, message);
        };
    }
}
