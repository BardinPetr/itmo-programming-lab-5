package ru.bardinpetr.itmo.lab5.network.transport.impl;

import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IClientTransport;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class UDPClientTransport implements IClientTransport<SocketMessage> {
    public UDPClientTransport(InetSocketAddress serverAddr) {
    }

    @Override
    public void send(SocketMessage data) {

    }

    @Override
    public SocketMessage receive(Duration timeout) throws TimeoutException, IOException {
        return null;
    }
}
