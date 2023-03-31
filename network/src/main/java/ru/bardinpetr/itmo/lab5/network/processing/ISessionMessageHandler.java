package ru.bardinpetr.itmo.lab5.network.processing;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.session.Session;

public interface ISessionMessageHandler<K> {
    void handle(Session<K> session, SocketMessage message);
}
