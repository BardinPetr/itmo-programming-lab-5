package ru.bardinpetr.itmo.lab5.server.auth.recv;

import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.LoginResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserExistsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserNotFoundException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.AuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;
import ru.bardinpetr.itmo.lab5.server.auth.passwords.IPasswordController;
import ru.bardinpetr.itmo.lab5.server.auth.passwords.SHAPasswordController;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.UsersPGDAO;
import ru.bardinpetr.itmo.lab5.server.db.dto.UserDTO;

import java.util.Arrays;

public class DBAuthenticationReceiver implements AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> {
    private final IPasswordController pc = new SHAPasswordController();
    private final UsersPGDAO usersDBDAO;

    public DBAuthenticationReceiver(UsersPGDAO usersDBDAO) {
        this.usersDBDAO = usersDBDAO;
    }

    @Override
    public Authentication authorize(DefaultAuthenticationCredentials request) {
        var authObj = usersDBDAO.selectByUsername(request.getUsername());
        if (authObj == null) return new Authentication(
                Authentication.AuthenticationStatus.INVALID
        );

        var hashedPassword = pc.getHash(
                request.getPassword(),
                authObj.getSalt()
        );

        return new Authentication(
                Arrays.equals(authObj.getHashedPassword(), hashedPassword) ?
                        Authentication.AuthenticationStatus.NORMAL :
                        Authentication.AuthenticationStatus.INVALID,
                authObj.getId()
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

        if (usersDBDAO.selectByUsername(creds.getUsername()) != null)
            throw new UserExistsException();


        usersDBDAO.insert(
                new UserDTO(
                        creds.getUsername(),
                        creds.getPassword()
                )
        );

        return new DefaultLoginResponse(creds.getUsername());
    }
}
