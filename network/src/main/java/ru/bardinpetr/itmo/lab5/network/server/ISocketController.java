package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;

public interface ISocketController {
    void subscribe(IMessageHandler handler);

    void subscribe(SocketMessage.CommandType type, IMessageHandler handler);

    void write(SocketMessage message);
}
