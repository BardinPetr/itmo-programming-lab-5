package ru.bardinpetr.itmo.lab5.clientgui.api;

import ru.bardinpetr.itmo.lab5.client.api.auth.impl.RAMCredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.api.connectors.net.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.api.events.APIPoolingEventSource;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.JWTAuthConnector;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAPICommandAuthenticator;

import java.net.InetSocketAddress;
import java.time.Duration;

public class APIConnectorFactory {

    public static void create() {
        var apiCredStorage = new RAMCredentialsStorage<StoredJWTCredentials>();
        var defaultAddress = new InetSocketAddress("localhost", 5000);
        var baseAPI = new UDPAPIClientFactory(defaultAddress).create();
        var authedAPI = new JWTAuthConnector(
                JWTAPICommandAuthenticator.getInstance(),
                apiCredStorage,
                baseAPI
        );
        var connector = new QueuedAPIConnector(authedAPI);

        var eventConsumer = new APIPoolingEventSource(connector, Duration.ofSeconds(1));

        APIProvider.setCredentialsStorage(apiCredStorage);
        APIProvider.setConnector(connector);
        APIProvider.setPoolingEventSource(eventConsumer);
    }
}
