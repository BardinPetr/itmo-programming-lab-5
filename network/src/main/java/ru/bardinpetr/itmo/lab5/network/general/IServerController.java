package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.net.SocketAddress;

public interface IServerController {
    void send(SocketAddress address, SocketMessage msg);

    Pair<SocketAddress, SocketMessage> receive();

}
