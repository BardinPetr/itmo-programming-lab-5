package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bardinpetr.itmo.lab5.network.app.server.models.Session;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedSession<K> extends Session<K> {
    private Authentication auth;

    public AuthenticatedSession(K address, State state, Authentication auth) {
        super(address, state);
        this.auth = auth;
    }

    public static <K> AuthenticatedSession<K> of(Session<K> original, Authentication auth) {
        return new AuthenticatedSession<>(original.getAddress(), original.getState(), auth);
    }
}
