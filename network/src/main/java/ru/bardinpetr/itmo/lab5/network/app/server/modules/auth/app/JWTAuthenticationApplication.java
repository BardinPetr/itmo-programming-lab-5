package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app;

import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.api.APICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.AuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.JWTAuthenticationReceiverAdapter;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.storage.JWTKeyProvider;

public class JWTAuthenticationApplication extends AuthenticationApplication<JWTAuthenticationCredentials, JWTLoginResponse> {
    public JWTAuthenticationApplication(APICommandAuthenticator<JWTAuthenticationCredentials> commandAuthenticator, AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> authenticationReceiver, JWTKeyProvider keyProvider) {
        super(
                commandAuthenticator,
                new JWTAuthenticationReceiverAdapter(authenticationReceiver, keyProvider)
        );
    }


}
