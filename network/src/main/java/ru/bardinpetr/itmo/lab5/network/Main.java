package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerTransport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;


public class Main {
    public static void main(String[] args) throws IOException {
        SetupJUL.loadProperties(Main.class);

        var channel = DatagramChannel.open();
        channel.configureBlocking(false);

        channel.socket().bind(new InetSocketAddress("localhost", 1249));
        var server = new UDPServerTransport(channel);

        server.subscribe((sender, message) -> {
            message.setCmdType(SocketMessage.CommandType.ACK);
            server.send(sender, message);
        });


        server.run();
    }
}
