package ru.bardinpetr.itmo.lab5.server.auth;

import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.LoginResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserExistsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserNotFoundException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.AuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;

import java.util.HashMap;
import java.util.Map;

public class DBAuthenticationReceiver implements AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> {
    private final Map<String, String> testAuth = new HashMap<>() {{
        put("u", "p");
    }};

    @Override
    public Authentication authorize(DefaultAuthenticationCredentials request) {
        var pass = testAuth.get(request.getUsername());
        return new Authentication(
                (pass != null && pass.equals(request.getPassword())) ? Authentication.AuthenticationStatus.NORMAL : Authentication.AuthenticationStatus.INVALID,
                request.getUsername()
        );
    }

    @Override
    public LoginResponse login(LoginCommand command) throws UserNotFoundException {
        DefaultAuthenticationCredentials creds = command.getCredentials();
        Authentication res = authorize(creds);

        if (res.getStatus() != Authentication.AuthenticationStatus.NORMAL)
            throw new UserNotFoundException();

        return new DefaultLoginResponse(creds.getUsername());
    }

    @Override
    public DefaultLoginResponse register(RegisterCommand command) throws UserExistsException {
        DefaultAuthenticationCredentials creds = command.getCredentials();

        if (testAuth.containsKey(creds.getUsername()))
            throw new UserExistsException();

        testAuth.put(
                creds.getUsername(),
                creds.getPassword()
        );

        return new DefaultLoginResponse(creds.getUsername());
    }
}
