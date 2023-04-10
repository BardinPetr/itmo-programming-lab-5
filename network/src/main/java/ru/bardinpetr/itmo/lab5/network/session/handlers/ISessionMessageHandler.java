package ru.bardinpetr.itmo.lab5.network.session.handlers;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

@Deprecated(forRemoval = true)
public interface ISessionMessageHandler<K> {
    void handle(Session<K> session, SocketMessage message);
}
