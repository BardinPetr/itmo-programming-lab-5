package ru.bardinpetr.itmo.lab5.network.transport.server;

import ru.bardinpetr.itmo.lab5.network.transport.interfaces.IServerTransport;
import ru.bardinpetr.itmo.lab5.network.transport.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.server.multithreading.SessionFrameRouter;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPServerFactory {
    public static IServerTransport<SocketAddress, SocketMessage> create(int port) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(true);
            channel.socket().bind(new InetSocketAddress("localhost", port));
            return new SessionFrameRouter(channel);
//            return new UDPServerTransport(channel);
        } catch (Exception e) {
            System.err.println("Can't start server: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
