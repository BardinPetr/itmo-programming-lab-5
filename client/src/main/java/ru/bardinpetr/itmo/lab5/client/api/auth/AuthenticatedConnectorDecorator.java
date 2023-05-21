package ru.bardinpetr.itmo.lab5.client.api.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.connectors.APIConnectorDecorator;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.api.APICommandAuthenticator;

/**
 * API connector decorator for authentication credentials injection
 *
 * @param <C> credentials type
 */
public class AuthenticatedConnectorDecorator<C extends AuthenticationCredentials> extends APIConnectorDecorator {

    private final APICommandAuthenticator<C> authenticator;
    private final ICredentialsStorage<C> storage;

    public AuthenticatedConnectorDecorator(APICommandAuthenticator<C> commandAuthenticator, ICredentialsStorage<C> storage, APIClientConnector decoratee) {
        super(decoratee);
        this.storage = storage;
        this.authenticator = commandAuthenticator;
    }

    @Override
    public APICommandResponse call(APICommand cmd) throws APIClientException {
        var creds = storage.getCredentials();
        var authed = authenticator.authenticate(cmd, creds);
        return decoratee.call(authed);
    }
}
