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
@Deprecated(forRemoval = true)
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
        log.info("Starting handshake with %s (step 1)".formatted(address));

        if (sessions.containsKey(address)) {
            throw new InvalidSessionException("Session already existed");
        }

        var session = new Session<>(address);
        sessions.put(address, session);

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.INIT, null),
                (success, payload) -> {
                    log.info("Handshake with %s (step 2): got ACK ON INIT".formatted(address));
                    sessions.get(address).setState(Session.State.WAITING_HANDSHAKE);
                }
        );
    }

    /**
     * End session (for external use)
     *
     * @param address
     */
    public void closeSession(K address) {
        log.info("Initiating session end with %s (step 1)".formatted(address));

        var session = sessions.get(address);

        if (session == null) {
            throw new InvalidSessionException("Session already existed");
        }

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.HALT, null),
                (success, payload) -> {
                    log.debug("Session ending with %s (step 2): got ACK on our HALT".formatted(address));
                    session.setState(Session.State.DEAD);
                }
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
        log.info("Got request to init session with %s (step 2)".formatted(address));

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

        log.info("Sending session init invite back to %s (step 3)".formatted(address));
        sendController.send(session, BaseMessage.ACK(), null);

        sendController.send(
                session,
                new BaseMessage(SocketMessage.CommandType.INIT, null),
                (success, payload) -> {
                    log.info("Established session with %s".formatted(address));
                    sessions.get(address).setState(Session.State.OPERATING);
                }
        );
    }

    /**
     * Called when this party initialized handshake, counterpart sent ACK on our INIT and offered its INIT
     *
     * @param session session in state of WAITING_HANDSHAKE
     * @param message INIT message of opponent
     */
    private void acceptLastHandshake(Session<K> session, SocketMessage message) {
        log.info("Established session with %s".formatted(session.getAddress()));
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
        log.debug("Got request to end session from %s".formatted(address));
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
        log.debug("Got DATA message from %s: %s".formatted(session.getAddress(), message));
        if (message.isResponse()) {
            sendController.onReceivedDataResponse(message);
        } else {
            replyController.onReceivedDataRequest(session, message);
        }
    }

    private void handleACKCmd(Session<K> session, SocketMessage message) {
        log.debug("Received ACK from %s".formatted(session.getAddress()));
        sendController.onReceivedACK(message);
    }

    private IMessageHandler<K> loadSessionAdapter(ISessionMessageHandler<K> inputEvent) {
        return (sender, message) -> {
            Session<K> curSession = sessions.get(sender);
            if (curSession != null) {
                var state = curSession.registerIncoming(message);
                log.debug("Processing message %s for session: %s".formatted(state, curSession));

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
