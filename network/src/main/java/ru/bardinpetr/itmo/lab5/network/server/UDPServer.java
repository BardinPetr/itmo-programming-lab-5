package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.server.errors.ServerStartException;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPServer {

    private final InetSocketAddress bindAddr;
    private DatagramChannel channel = null;
    private boolean running = false;

    public UDPServer(InetSocketAddress bindAddr) {
        this.bindAddr = bindAddr;
    }

    private void run() {
        if (channel == null)
            throw new ServerStartException("not initialized");

        while (running) {
            if (!channel.isConnected()) {
                stop();
                return;
            }
        }
    }

    public void init() throws ServerStartException {
        if (channel != null) {
            stop();
        }

        try {
            channel = DatagramChannel
                    .open()
                    .bind(bindAddr);
        } catch (IOException e) {
            throw new ServerStartException(e);
        }

        running = true;
        run();
    }

    public void stop() {
        try {
            channel.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        channel = null;
        running = false;
    }
}
