package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces;

import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.AuthenticationRegistrationResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.commands.RegisterAPICommand;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;

public interface AuthenticationReceiver {

    Authentication authorize(AuthenticationCredentials request);

    AuthenticationRegistrationResponse register(RegisterAPICommand command);
}

