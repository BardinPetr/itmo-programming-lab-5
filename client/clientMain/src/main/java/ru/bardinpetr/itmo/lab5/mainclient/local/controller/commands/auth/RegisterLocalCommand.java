package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;

/**
 * Register implementation of authentication command
 */
public class RegisterLocalCommand<T extends AuthenticationCredentials> extends AuthLocalCommand<T> {

    public RegisterLocalCommand(APIClientConnector api, UIReceiver ui, ICredentialsStorage<T> credentialsStorage) {
        super(
                new RegisterCommand(),
                api, ui, credentialsStorage
        );
    }

    @Override
    protected void onSuccess(AuthenticationCredentials credentials, APICommandResponse resp) {
        this.credentialsStorage.setCredentials((T) credentials);
    }

    @Override
    protected String getPrompt() {
        return "Enter username and password for registration";
    }
}
