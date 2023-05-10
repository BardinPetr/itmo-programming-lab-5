package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

/**
 * Login implementation of authentication command
 */
public class LoginLocalCommand extends AuthLocalCommand {

    public LoginLocalCommand(APIClientConnector api, UIReceiver ui, ICredentialsStorage<DefaultAuthenticationCredentials> credentialsStorage) {
        super(
                new LoginCommand(),
                api, ui, credentialsStorage
        );
    }

    @Override
    protected void onSuccess(DefaultAuthenticationCredentials credentials, APICommandResponse resp) {
        this.credentialsStorage.setCredentials(credentials);
    }

    @Override
    protected String getPrompt() {
        return "Please login with username and password";
    }
}
