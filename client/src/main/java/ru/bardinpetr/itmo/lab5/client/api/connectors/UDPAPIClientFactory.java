package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.network.app.client.impl.SocketAPIClient;
import ru.bardinpetr.itmo.lab5.network.transport.client.UDPClientTransport;

import java.net.InetSocketAddress;

public class UDPAPIClientFactory extends AbstractAPIClientReceiverFactory {


    private final InetSocketAddress serverAddress;

    public UDPAPIClientFactory(InetSocketAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public APIClientReceiver create() {
        return new NetworkServerConnector(
                new SocketAPIClient(new UDPClientTransport(serverAddress))
        );
    }
}
