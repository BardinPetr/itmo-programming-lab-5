package ru.bardinpetr.itmo.lab5.network;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.server.interfaces.IChannelController;
import ru.bardinpetr.itmo.lab5.network.session.SessionController;

import java.io.IOException;


@Slf4j
public class Main {
    public static void main(String[] args) throws IOException {
//        var server = new UDPServer(new InetSocketAddress("localhost", 1249));
//        server.run();
        log.error("test");

        var ch = new IChannelController<Integer>() {

            @Override
            public void write(Integer address, SocketMessage message) {

            }

            @Override
            public void subscribe(IMessageHandler<Integer> handler, SocketMessage.CommandType... types) {

            }
        };

        var sess = new SessionController<>(ch);

    }
}
