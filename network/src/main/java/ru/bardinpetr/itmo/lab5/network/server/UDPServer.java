package ru.bardinpetr.itmo.lab5.network.server;

import ru.bardinpetr.itmo.lab5.network.general.UDPServerController;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.server.interfaces.IChannelController;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.Map;

public class UDPServer extends UDPServerController implements IChannelController<SocketAddress> {
    private final Map<SocketMessage.CommandType, IMessageHandler<SocketAddress>> serverCmdMap = new HashMap<>();


    public UDPServer(SocketAddress address) throws IOException {
        super(DatagramChannel.open().bind(address));
    }

    public void run() {

        System.out.println("listening..");

        SocketAddress clientAdr;

        while (true) {

            SocketMessage msg;
            var pair = receive();
            clientAdr = pair.getFirst();
            msg = pair.getSecond();

            if (serverCmdMap.containsKey(msg.getCmdType())) {
                serverCmdMap.get(msg.getCmdType()).handle(clientAdr, msg);
            }


        }

    }

    @Override
    public void subscribe(IMessageHandler<SocketAddress> handler, SocketMessage.CommandType... types) {
        for (var i : types) {
            serverCmdMap.put(i, handler);
        }
    }

    @Override
    public void write(SocketAddress address, SocketMessage message) {
        send(address, message);
    }
}
