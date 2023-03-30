package ru.bardinpetr.itmo.lab5.network.session;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
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

        controller.subscribe(this::handleACK, SocketMessage.CommandType.ACK, SocketMessage.CommandType.NACK);
        controller.subscribe(this::handleSession, SocketMessage.CommandType.INIT, SocketMessage.CommandType.HALT);
        controller.subscribe(this::handleData, SocketMessage.CommandType.DATA);
    }

    private void handleSession(K sender, SocketMessage message) {
        var curSession = getSession(sender);
    }

    private void handleData(K sender, SocketMessage message) {
        var curSession = getSession(sender);
        sendController.onReceivedData(curSession, message);
    }

    private void handleACK(K sender, SocketMessage message) {
        sendController.onReceivedACK(getSession(sender), message);
    }

    private Session<K> getSession(K sender) {
        return sessions.get(sender);
    }
}
