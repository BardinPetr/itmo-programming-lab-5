package ru.bardinpetr.itmo.lab5.server.auth;

import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.AuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.AuthenticationRegistrationResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.commands.RegisterAPICommand;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;

public class DBAuthenticationReceiver implements AuthenticationReceiver {
    @Override
    public Authentication authorize(AuthenticationCredentials request) {
        return null;
    }

    @Override
    public AuthenticationRegistrationResponse register(RegisterAPICommand command) {
        return null;
    }
}
