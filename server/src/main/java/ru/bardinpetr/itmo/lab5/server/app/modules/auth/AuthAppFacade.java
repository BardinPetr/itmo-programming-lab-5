package ru.bardinpetr.itmo.lab5.server.app.modules.auth;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAPICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAuthenticationApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.storage.JWTHMACKeyProvider;
import ru.bardinpetr.itmo.lab5.server.auth.recv.DBAuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.server.db.dao.DBTableProvider;

import java.util.Base64;
import java.util.List;

import static ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAuthenticationReceiverAdapter.KID_ACCESS;
import static ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.jwt.JWTAuthenticationReceiverAdapter.KID_REFRESH;

@Slf4j
public class AuthAppFacade {

    private boolean isDebug = false;

    public void setDebug(boolean debug) {
        isDebug = debug;
    }

    public JWTAuthenticationApplication create(DBTableProvider tableProvider) {
        var authReceiver = new DBAuthenticationReceiver(tableProvider.getUsers());

        var jwtKeyProvider = new JWTHMACKeyProvider();
        jwtKeyProvider.registerGenerate(KID_ACCESS);
        jwtKeyProvider.registerGenerate(KID_REFRESH);

        if (isDebug)
            for (var i : List.of(KID_ACCESS, KID_REFRESH))
                log.debug("HS512 key for kid {}: {}", i, new String(Base64.getEncoder().encode(jwtKeyProvider.resolveSigningKey(i).getEncoded())));

        return new JWTAuthenticationApplication(
                JWTAPICommandAuthenticator.getInstance(),
                authReceiver,
                jwtKeyProvider
        );
    }
}

