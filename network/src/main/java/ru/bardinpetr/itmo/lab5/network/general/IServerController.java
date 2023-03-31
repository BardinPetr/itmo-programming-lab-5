package ru.bardinpetr.itmo.lab5.network.general;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.utils.Pair;

import java.io.IOException;
import java.net.SocketAddress;

public interface IServerController {
    void send(SocketMessage msg, SocketAddress address) throws IOException;

    Pair<SocketAddress, SocketMessage> receive() throws IOException;

}
