package ru.bardinpetr.itmo.lab5.network.transport.impl;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;
import ru.bardinpetr.itmo.lab5.network.transport.handlers.IMessageHandler;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
public class UDPServerTransport implements IServerTransport<InetSocketAddress, SocketMessage> {

    @Override
    public void send(InetSocketAddress recipient, SocketMessage data) {
        log.info("Sending UDP to {}: {}", recipient, data);
    }

    @Override
    public void subscribe(IMessageHandler<InetSocketAddress, SocketMessage> handler) {
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            handler.handle(
                                    new InetSocketAddress(123),
                                    new SocketMessage(
                                            SocketMessage.CommandType.DATA, 12L, 0L,
                                            (new JSONSerDesService<>(APICommand.class)).serialize(new ShowCommand())
                                    )
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                },
                1000,
                1000000
        );
    }
}
