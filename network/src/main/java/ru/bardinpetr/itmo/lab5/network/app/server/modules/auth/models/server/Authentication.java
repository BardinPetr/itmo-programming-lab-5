package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authentication {
    private AuthenticationStatus status = AuthenticationStatus.GUEST;
    private Integer userHandle = null;

    public Authentication(AuthenticationStatus status) {
        this.status = status;
    }


    public enum AuthenticationStatus {
        GUEST,
        INVALID,
        NORMAL
    }
}
