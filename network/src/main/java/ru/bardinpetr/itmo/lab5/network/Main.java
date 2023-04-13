package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.server.UDPSelectorServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;


public class Main {
    public static void main(String[] args) throws IOException {
//        SetupJUL.loadProperties(Main.class);

        var channel = DatagramChannel.open();
        channel.configureBlocking(false);

        channel.socket().bind(new InetSocketAddress("localhost", 1249));
        var server = new UDPSelectorServer(channel);

        server.subscribe((sender, message) -> {
            message.setCmdType(SocketMessage.CommandType.ACK);
            server.send(sender, message);
        });


        server.run();
    }
}
