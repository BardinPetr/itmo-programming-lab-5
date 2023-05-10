package ru.bardinpetr.itmo.lab5.client.api.connectors.net;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.AbstractAPIClientReceiverFactory;
import ru.bardinpetr.itmo.lab5.network.app.client.impl.SocketAPIClient;
import ru.bardinpetr.itmo.lab5.network.transport.client.UDPClientTransport;

import java.net.InetSocketAddress;

public class UDPAPIClientFactory extends AbstractAPIClientReceiverFactory {


    private final InetSocketAddress serverAddress;

    public UDPAPIClientFactory(InetSocketAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public APIClientConnector create() {
        return new NetworkServerConnector(
                new SocketAPIClient(new UDPClientTransport(serverAddress))
        );
    }
}
