package ru.bardinpetr.itmo.lab5.network.transport.server;

import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPServerFactory {
    public static UDPServerTransport create(int port) {
        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.socket().bind(new InetSocketAddress("localhost", port));
            return new (channel);
//            return new UDPServerTransport(channel);
        } catch (Exception e) {
            System.err.println("Can't start server: " + e.getMessage());
            System.exit(0);
        }
        return null;
    }
}
