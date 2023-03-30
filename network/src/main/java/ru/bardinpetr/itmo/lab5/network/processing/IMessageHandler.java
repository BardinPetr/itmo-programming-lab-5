package ru.bardinpetr.itmo.lab5.network.processing;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

import java.net.InetSocketAddress;

public interface IMessageHandler {
    void handle(InetSocketAddress sender, SocketMessage message);
}
