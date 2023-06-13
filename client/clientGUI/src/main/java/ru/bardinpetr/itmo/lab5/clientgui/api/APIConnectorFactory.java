package ru.bardinpetr.itmo.lab5.clientgui.api;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.impl.RAMCredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.api.connectors.net.UDPAPIClientFactory;
import ru.bardinpetr.itmo.lab5.client.api.events.APIPoolingEventSource;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.JWTAuthConnector;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAPICommandAuthenticator;

import javax.swing.*;
import java.net.InetSocketAddress;
import java.time.Duration;

public class APIConnectorFactory {

    public static void create() {
        var apiCredStorage = new RAMCredentialsStorage<StoredJWTCredentials>();
        var defaultAddress = new InetSocketAddress("localhost", 5000);
        APIClientConnector baseAPI;
        while (true) {
            try {
                baseAPI = new UDPAPIClientFactory(defaultAddress).create();
                break;
            } catch (RuntimeException canNotConnect) {
                var resources = UIResources.getInstance();
                Object[] options = {
                        resources.get("optionalAnswers.Yes"),
                        resources.get("optionalAnswers.No")
                };
                var ans = JOptionPane.showOptionDialog(
                        new JPanel(),
                        resources.get("APIConnectorFactory.connectionError.text"),
                        resources.get("command.error.requestFailed"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,     //do not use a custom Icon
                        options,
                        options[0]
                );
                if (ans == 1) System.exit(0);

            }

        }
        var authedAPI = new JWTAuthConnector(
                JWTAPICommandAuthenticator.getInstance(),
                apiCredStorage,
                baseAPI
        );
        var queuedAPI = new QueuedAPIConnector(authedAPI);
        var connector = new UIReloginAPIConnector(queuedAPI);

        authedAPI.setLoginPageStarter(connector::logout);

        var eventConsumer = new APIPoolingEventSource(connector, Duration.ofSeconds(1));

        APIProvider.setCredentialsStorage(apiCredStorage);
        APIProvider.setConnector(connector);
        APIProvider.setPoolingEventSource(eventConsumer);
    }
}
