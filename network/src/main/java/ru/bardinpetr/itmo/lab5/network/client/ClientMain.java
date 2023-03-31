package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPClient;

import java.io.IOException;
import java.net.InetSocketAddress;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        var client = new UDPClient(new InetSocketAddress("localhost", 1249));
        var t = new SocketMessage(SocketMessage.CommandType.HALT, 123, 2314, false, "Hi".getBytes());

        client.send(t);

    }
}
