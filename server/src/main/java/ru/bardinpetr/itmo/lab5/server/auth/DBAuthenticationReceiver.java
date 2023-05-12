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
import ru.bardinpetr.itmo.lab5.server.auth.interfaces.IPasswordController;
import ru.bardinpetr.itmo.lab5.server.auth.interfaces.SQLAuthDAO;
import ru.bardinpetr.itmo.lab5.server.auth.models.AuthorizationObject;

import java.util.Arrays;

public class DBAuthenticationReceiver implements AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> {
    private final IPasswordController pc = new SHAPasswordController();
    private SQLAuthDAO sqlController;

    @Override
    public Authentication authorize(DefaultAuthenticationCredentials request) {
        var authObj = sqlController.getByUserName(request.getUsername());

        var hashedPassword = pc.getHash(
                request.getPassword(),
                authObj.getSalt()
        );

        return new Authentication(
                (authObj != null && Arrays.equals(authObj.getHashedPassword(), hashedPassword)) ?
                        Authentication.AuthenticationStatus.NORMAL :
                        Authentication.AuthenticationStatus.INVALID,
                request.getUsername()
        );
    }

    @Override
    public LoginResponse login(LoginCommand command) throws UserNotFoundException {
        var creds = command.getCredentials();
        var res = authorize(creds);

        if (res.getStatus() != Authentication.AuthenticationStatus.NORMAL)
            throw new UserNotFoundException();

        return new DefaultLoginResponse(creds.getUsername());
    }

    @Override
    public DefaultLoginResponse register(RegisterCommand command) throws UserExistsException {
        var creds = command.getCredentials();

        if (sqlController.checkUser(creds.getUsername()))
            throw new UserExistsException();


        sqlController.insert(
                new AuthorizationObject(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );

        return new DefaultLoginResponse(creds.getUsername());
    }
}
