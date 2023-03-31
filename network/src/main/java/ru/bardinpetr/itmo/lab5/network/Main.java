package ru.bardinpetr.itmo.lab5.network;

import ru.bardinpetr.itmo.lab5.network.server.UDPServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Main {
    public static void main(String[] args) throws IOException {
        var server = new UDPServer(new InetSocketAddress("localhost", 1249));
        server.run();
    }
}
