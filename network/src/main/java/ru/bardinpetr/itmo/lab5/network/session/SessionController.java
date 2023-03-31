package ru.bardinpetr.itmo.lab5.network.session;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.processing.ISessionMessageHandler;
import ru.bardinpetr.itmo.lab5.network.server.IChannelController;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for handling sessions for UDP
 *
 * @param <K> type of object, that uniquely identifies client before message is parsed
 */
public class SessionController<K> {

    private final Map<K, Session<K>> sessions = new HashMap<>();
    private final IChannelController<K> controller;
    private final SessionSendController<K> sendController;


    public SessionController(IChannelController<K> controller) {
        this.sendController = new SessionSendController<>(controller);
        this.controller = controller;

        controller.subscribe(
                loadSessionAdapter(this::handleACKCmd),
                SocketMessage.CommandType.ACK, SocketMessage.CommandType.NACK
        );
        controller.subscribe(
                loadSessionAdapter(this::handleSessionInitCmd),
                SocketMessage.CommandType.INIT
        );
        controller.subscribe(
                loadSessionAdapter(this::handleSessionHaltCmd),
                SocketMessage.CommandType.HALT
        );
        controller.subscribe(
                loadSessionAdapter(this::handleDataCmd),
                SocketMessage.CommandType.DATA
        );
    }

    private void handleSessionInitCmd(Session<K> session, SocketMessage message) {
    }

    private void handleSessionHaltCmd(Session<K> session, SocketMessage message) {
    }

    private void handleDataCmd(Session<K> session, SocketMessage message) {
        if (message.isResponse()) {
            sendController.onReceivedDataResponse(message);
        } else {
            // TODO handle request for us
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
