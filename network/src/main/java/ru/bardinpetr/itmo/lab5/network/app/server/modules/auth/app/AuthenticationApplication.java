package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.LoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponseStatus;
import ru.bardinpetr.itmo.lab5.network.app.server.AbstractApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.api.APICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.InvalidCredentialsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserExistsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.Authentication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.recv.AuthenticationReceiver;

/**
 * Application for user authentication (in command headers) and registration (via RegisterAPICommand).
 *
 * @param <C> type of AuthenticationCredentials used
 * @param <R> type of RegistrationResponse used
 */
@Slf4j
public class AuthenticationApplication<C extends AuthenticationCredentials, R extends LoginResponse> extends AbstractApplication {

    private final AuthenticationReceiver<C, R> authenticationReceiver;
    private final APICommandAuthenticator<C> commandAuthenticator;

    public AuthenticationApplication(APICommandAuthenticator<C> commandAuthenticator, AuthenticationReceiver<C, R> authenticationReceiver) {
        this.authenticationReceiver = authenticationReceiver;
        this.commandAuthenticator = commandAuthenticator;

        on(RegisterCommand.class, this::registerUser);
        on(LoginCommand.class, this::loginUser);
    }

    /**
     * Handle user login command
     */
    protected void loginUser(AppRequest request) {
        var resp = request.response();

        var cmd = (LoginCommand<?>) request.payload();

        var logID = cmd.getCredentials().safeIdentifier();
        log.info("Login request for {}", logID);
        try {
            var loginResponse = authenticationReceiver.login(cmd);

            resp.from(cmd.createResponse().setData(loginResponse));
        } catch (Throwable e) {
            log.error("Login authentication failed for {}: {}", logID, e.getMessage());
            resp.status(APIResponseStatus.AUTH_ERROR);
        }

        resp.send();
    }

    /**
     * Handle user registration command
     */
    protected void registerUser(AppRequest request) {
        var resp = request.response();

        RegisterCommand cmd = (RegisterCommand) request.payload();

        var logID = cmd.getCredentials().safeIdentifier();
        log.info("Register request for {}", logID);
        try {
            var registerResponse = authenticationReceiver.register(cmd);

            resp.from(cmd.createResponse().setData(registerResponse));
        } catch (UserExistsException e) {
            resp.status(APIResponseStatus.AUTH_ERROR).message("AuthenticationApplication.userAlreadyExists.error");
        } catch (InvalidCredentialsException e) {
            log.error("Register failed for {}: {}", logID, e.getMessage());
            resp.status(APIResponseStatus.AUTH_ERROR).message("CredentialsValidator.invalid.tex");
        } catch (Throwable e) {
            log.error("Register failed for {}: {}", logID, e.getMessage());
            resp.status(APIResponseStatus.AUTH_ERROR).message("loginPage.error.authorizationFailed");
        }

        resp.send();
    }

    /**
     * Replace Session with AuthenticatedSession for every request performing authentication.
     * No auth creds in request will result in GUEST status.
     *
     * @param request request to be processed
     */
    @Override
    protected void apply(AppRequest request) {
        var authRequest = commandAuthenticator.extractAuth(request.payload());

        if (authRequest == null) {
            log.info("Request {} does not contain auth data", request.id());
            updateSession(
                    request,
                    new Authentication(Authentication.AuthenticationStatus.GUEST)
            );
            return;
        }


        Authentication result = new Authentication(Authentication.AuthenticationStatus.GUEST);
        try {
            result = authenticationReceiver.authorize(authRequest);
        } catch (Throwable e) {
            log.error("Request authentication failed for {}: {}", authRequest.safeIdentifier(), e.getMessage());
        }

        log.info("Request authentication OK for {}", authRequest.safeIdentifier());
        updateSession(
                request,
                result
        );
    }

    /**
     * Replace AppRequest session with Authenticated one
     */
    private void updateSession(AppRequest request, Authentication authentication) {
        request.session().setAuth(authentication);
    }
}
