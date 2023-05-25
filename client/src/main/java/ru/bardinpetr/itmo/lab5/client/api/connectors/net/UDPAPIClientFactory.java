package ru.bardinpetr.itmo.lab5.client.api.connectors.net;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.AbstractAPIClientReceiverFactory;
import ru.bardinpetr.itmo.lab5.models.commands.api.PingCommand;
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
        try {
            var connector = new NetworkServerConnector(
                    new SocketAPIClient(new UDPClientTransport(serverAddress))
            );

            if (!connector.call(new PingCommand()).isSuccess())
                throw new RuntimeException("Server not responding");

            return connector;
        } catch (Throwable e) {
            System.err.printf("Could not connect to server: %s\n", e.getMessage());
            System.exit(1);
        }
        return null;
    }
}
