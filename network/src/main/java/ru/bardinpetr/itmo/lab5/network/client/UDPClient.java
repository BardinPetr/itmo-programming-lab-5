package ru.bardinpetr.itmo.lab5.network.client;

import ru.bardinpetr.itmo.lab5.network.general.ServerController;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPClient extends ServerController {
    private final SocketAddress serverAddress;


    public UDPClient(SocketAddress socketAddress) throws IOException {
        super(DatagramChannel.open().bind(null));
        this.serverAddress = socketAddress;
    }

    public void send(SocketMessage msg) throws IOException {
        super.send(msg, serverAddress);

        var pair = receive();
        if (pair.getSecond().getCmdType().equals(SocketMessage.CommandType.ACK)) {
            System.out.println("OK!");
        } else {
            System.out.println("NOT OK(");
        }
    }


}
