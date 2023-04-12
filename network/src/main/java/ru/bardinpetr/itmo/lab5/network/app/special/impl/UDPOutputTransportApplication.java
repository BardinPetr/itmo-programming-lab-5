package ru.bardinpetr.itmo.lab5.network.app.special.impl;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.serdes.JSONSerDesService;
import ru.bardinpetr.itmo.lab5.common.serdes.exceptions.SerDesException;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.special.AbstractOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;
import ru.bardinpetr.itmo.lab5.network.transport.IServerTransport;

import java.net.InetSocketAddress;

@Slf4j
public class UDPOutputTransportApplication
        extends AbstractOutputTransportApplication<InetSocketAddress, SocketMessage> {

    private final JSONSerDesService<APICommandResponse> serDesService;

    public UDPOutputTransportApplication(IServerTransport<InetSocketAddress, SocketMessage> transport) {
        super(transport);
        serDesService = new JSONSerDesService<>(APICommandResponse.class);
    }

    @Override
    protected SocketMessage serialize(APICommandResponse request) {
        try {
            byte[] data = serDesService.serialize(request);
            return new SocketMessage(data);
        } catch (SerDesException e) {
            return null;
        }
    }
}
