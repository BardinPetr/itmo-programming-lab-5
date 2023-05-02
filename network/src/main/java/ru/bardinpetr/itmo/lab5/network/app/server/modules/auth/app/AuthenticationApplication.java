package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.app.server.AbstractApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces.AuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.APICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.commands.RegisterAPICommand;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.AuthenticatedSession;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;

/**
 * Application for user authentication (in command headers) and registration (via RegisterAPICommand).
 */
@Slf4j
public class AuthenticationApplication extends AbstractApplication {

    private final AuthenticationReceiver authenticationReceiver;

    public AuthenticationApplication(AuthenticationReceiver authenticationReceiver) {
        this.authenticationReceiver = authenticationReceiver;

        on(RegisterAPICommand.class, this::registerUser);
    }


    /**
     * Handle user registration command
     */
    private void registerUser(AppRequest request) {
        RegisterAPICommand cmd = (RegisterAPICommand) request.payload();
        var resp = authenticationReceiver.register(cmd);

        request
                .response()
                .from(
                        cmd.createResponse()
                                .setData(resp)
                )
                .send();
    }

    /**
     * Replace Session with AuthenticatedSession for every request performing authentication.
     * No auth creds in request will result in GUEST status.
     *
     * @param request request to be processed
     */
    @Override
    protected void beforeTerminating(AppRequest request) {
        var authRequest =
                APICommandAuthenticator.getInstance()
                        .extractAuth(request.payload());

        if (authRequest == null) {
            updateSession(
                    request,
                    new Authentication(Authentication.AuthenticationStatus.GUEST)
            );
            return;
        }

        Authentication result = new Authentication(Authentication.AuthenticationStatus.GUEST);
        try {
            result = authenticationReceiver.authorize(authRequest);
        } catch (Exception ignored) {
        }

        updateSession(
                request,
                result
        );
    }

    /**
     * Replace AppRequest session with Authenticated one
     */
    private void updateSession(AppRequest request, Authentication authentication) {
        request.setSession(AuthenticatedSession.of(
                request.session(),
                authentication
        ));
    }
}
