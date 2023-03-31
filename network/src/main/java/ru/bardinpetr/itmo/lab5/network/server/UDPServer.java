package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.general.ServerController;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;

public class UDPServer extends ServerController {

    public UDPServer(SocketAddress address) throws IOException {
        super(DatagramChannel.open().bind(address));
    }

    public void run() {

        System.out.println("listening..");

        SocketAddress clientAdr;

        while (true) {

            SocketMessage msg;
            try {
                var pair = receive();
                clientAdr = pair.getFirst();
                msg = pair.getSecond();

                System.out.println(msg);
                send(new SocketMessage(SocketMessage.CommandType.NACK, 132, 2112, false, null), clientAdr);

            } catch (IOException e) {
                throw new RuntimeException(e);
                //continue;
            }
        }

    }
}
