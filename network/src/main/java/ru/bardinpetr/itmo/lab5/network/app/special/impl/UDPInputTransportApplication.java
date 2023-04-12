package ru.bardinpetr.itmo.lab5.network.app.special.impl;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.session.Session;
import ru.bardinpetr.itmo.lab5.network.app.special.AbstractInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;

import java.net.InetSocketAddress;

@Slf4j
public class UDPInputTransportApplication
        extends AbstractInputTransportApplication<APICommand, APICommandResponse, InetSocketAddress, SocketMessage> {


    private final JSONSerDesService<APICommand> serDesService;

    public UDPInputTransportApplication(IServerTransport<InetSocketAddress, SocketMessage> transport) {
        super(transport);
        serDesService = new JSONSerDesService<>(APICommand.class);
    }

    @Override
    protected Session<InetSocketAddress> supplySession(InetSocketAddress senderID, SocketMessage incomingMessage) {
        return new Session<>(senderID);
    }

    @Override
    protected APICommand deserialize(SocketMessage request) {
        try {
            return serDesService.deserialize(request.getPayload());
        } catch (SerDesException e) {
            return null;
        }
    }
}
