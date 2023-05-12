package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces;

import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.LoginResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.InvalidCredentialsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserExistsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserNotFoundException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;

/**
 * Service for validating of authentication requests
 *
 * @param <C> type of AuthenticationCredentials used
 * @param <R> type of RegistrationResponse used
 */
public interface AuthenticationReceiver<C extends AuthenticationCredentials, R extends LoginResponse> {

    Authentication authorize(C request);

    LoginResponse login(LoginCommand request) throws UserNotFoundException;

    LoginResponse register(RegisterCommand command) throws UserExistsException, InvalidCredentialsException;
}
