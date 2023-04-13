package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerTransport;

import java.io.IOException;
import java.nio.channels.DatagramChannel;


public class Main {
    public static void main(String[] args) throws IOException {
        SetupJUL.loadProperties(Main.class);

        DatagramChannel channel = null;
        UDPServerTransport server = UDPServerFactory.create(1249);

        server.subscribe((sender, message) -> {
            message.setCmdType(SocketMessage.CommandType.ACK);
            server.send(sender, message);
        });


        server.run();
    }
}
