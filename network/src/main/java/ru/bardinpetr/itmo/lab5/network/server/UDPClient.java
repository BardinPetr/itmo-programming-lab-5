package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.general.UDPServerController;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPClient extends UDPServerController {
    private final SocketAddress serverAddress;

    public UDPClient(SocketAddress socketAddress) throws IOException {
        super(DatagramChannel.open().bind(null));
        this.serverAddress = socketAddress;
    }
    public void send(SocketMessage msg) {
        super.send(serverAddress, msg);
    }


}
