package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api;

import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;


/**
 * Helper class for injecting authentication credentials into APICommand
 */
public final class APICommandAuthenticator {
    public static final String AUTH_HEADER = "Authentication";
    private static APICommandAuthenticator instance;

    private APICommandAuthenticator() {
    }

    public static APICommandAuthenticator getInstance() {
        if (instance == null)
            instance = new APICommandAuthenticator();
        return instance;
    }

    /**
     * Adds authentication header to existing API command
     *
     * @param decoratee command to authenticate
     * @param authData  credentials
     * @return command with headers set
     */
    public APICommand authenticate(APICommand decoratee, AuthenticationCredentials authData) {
        decoratee.getHeader().put(
                AUTH_HEADER,
                authData
        );
        return decoratee;
    }

    /**
     * Retrieve credentials from command processed by authenticate()
     *
     * @param command command with AUTH_HEADER set
     * @return credentials or null if header is not present
     */
    public AuthenticationCredentials extractAuth(APICommand command) {
        return (AuthenticationCredentials) command.getHeader().get(AUTH_HEADER);
    }
}
