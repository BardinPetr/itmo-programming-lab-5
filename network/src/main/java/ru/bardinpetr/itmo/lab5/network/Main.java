package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws IOException {
        var server = new UDPServer(new InetSocketAddress("localhost", 1249));

        server.subscribe((sender, message) -> {
            var t = new SocketMessage(SocketMessage.CommandType.NACK, message.getId() + 1, message.getReplyId(), false, sender.toString().getBytes());
            server.send(sender, t);
        }, SocketMessage.CommandType.NACK);

        server.subscribe((sender, message) -> {
            var t = new SocketMessage(SocketMessage.CommandType.ACK, message.getId() + 1, message.getReplyId(), false, sender.toString().getBytes());
            server.send(sender, t);
        }, SocketMessage.CommandType.ACK);

        server.subscribe((sender, message) -> {
                    var t = new SocketMessage(SocketMessage.CommandType.DATA, message.getId() + 1, message.getReplyId(), false, sender.toString().getBytes());
                    server.send(sender, t);
                },
                SocketMessage.CommandType.DATA);

        server.run();
    }
}
