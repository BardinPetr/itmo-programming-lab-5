package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

public class JWTRegisterLocalCommand extends LoginLocalCommand<JWTAuthenticationCredentials> {
    public JWTRegisterLocalCommand(APIClientConnector api, UIReceiver ui, ICredentialsStorage<JWTAuthenticationCredentials> credentialsStorage) {
        super(api, ui, credentialsStorage);
    }

    @Override
    protected void onSuccess(AuthenticationCredentials credentials, APICommandResponse resp) {
        var loginResponse = ((AuthCommand.AuthCommandResponse) resp).getData();
        var jwtData = (JWTLoginResponse) loginResponse;
        credentialsStorage.setCredentials(new JWTAuthenticationCredentials(
                jwtData.getAuthToken()
        ));
    }
}
