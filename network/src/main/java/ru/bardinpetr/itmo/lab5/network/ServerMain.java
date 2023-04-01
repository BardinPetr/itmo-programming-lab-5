package ru.bardinpetr.itmo.lab5.network;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.processing.IMessageHandler;
import ru.bardinpetr.itmo.lab5.network.server.interfaces.IChannelController;
import ru.bardinpetr.itmo.lab5.network.session.SessionController;

import java.io.IOException;


@Slf4j
public class ServerMain {
    public static void main(String[] args) throws IOException, InterruptedException {
        SetupJUL.loadProperties(ServerMain.class);

        var chSrv = new IChannelController<Integer>() {

            @Override
            public void write(Integer address, SocketMessage message) {
                log.debug("Sending %s to %s".formatted(message, address));
            }

            @Override
            public void subscribe(IMessageHandler<Integer> handler, SocketMessage.CommandType... types) {

            }
        };
        var chClt = new IChannelController<Integer>() {

            @Override
            public void write(Integer address, SocketMessage message) {
                log.debug("Sending %s to %s".formatted(message, address));
            }

            @Override
            public void subscribe(IMessageHandler<Integer> handler, SocketMessage.CommandType... types) {

            }
        };

        var sessSrv = new SessionController<>(chSrv);
        var sessClt = new SessionController<>(chClt);

        sessClt.beginSession(1);

        Thread.sleep(10000);
    }
}
