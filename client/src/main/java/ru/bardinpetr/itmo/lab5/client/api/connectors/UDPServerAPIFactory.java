package ru.bardinpetr.itmo.lab5.client.api.connectors;

import ru.bardinpetr.itmo.lab5.client.api.APIClientReceiver;
import ru.bardinpetr.itmo.lab5.network.client.impl.SocketAPIClient;
import ru.bardinpetr.itmo.lab5.network.transport.impl.UDPClientTransport;

import java.net.InetSocketAddress;

public class UDPServerAPIFactory extends AbstractAPIClientReceiverFactory {


    private final InetSocketAddress serverAddress;

    public UDPServerAPIFactory(InetSocketAddress serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public APIClientReceiver create() {
        return new NetworkServerConnector(
                new SocketAPIClient(new UDPClientTransport(serverAddress))
        );
    }
}
