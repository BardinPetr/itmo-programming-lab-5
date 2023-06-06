package ru.bardinpetr.itmo.lab5.client.api.connectors;

import lombok.Getter;
import lombok.Setter;
import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;

public class APIProvider {
    @Getter
    @Setter
    private static APIClientConnector connector;

    @Getter
    @Setter
    private static ICredentialsStorage<StoredJWTCredentials> credentialsStorage;
}
