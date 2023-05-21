package ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.interfaces;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import ru.bardinpetr.itmo.lab5.models.commands.auth.LoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.RegisterCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.InvalidCredentialsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserExistsException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.errors.UserNotFoundException;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.server.Authentication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.storage.JWTKeyProvider;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JWTAuthenticationReceiverAdapter implements AuthenticationReceiver<JWTAuthenticationCredentials, JWTLoginResponse> {
    public static final String KID_ACCESS = "main";
    public static final String KID_REFRESH = "refresh";
    private static final String ISSUER = "srvlab5";
    private static final String CLAIM_USERNAME = "username";
    private static final String CLAIM_ROLE = "role";

    private final AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> db;
    private final JWTKeyProvider keyProvider;
    private final JwtParser decoder;

    public JWTAuthenticationReceiverAdapter(AuthenticationReceiver<DefaultAuthenticationCredentials, DefaultLoginResponse> authenticationReceiver, JWTKeyProvider keyProvider) {
        this.db = authenticationReceiver;
        this.keyProvider = keyProvider;

        this.decoder = Jwts.parserBuilder()
                .setSigningKeyResolver(keyProvider.getDecodeKeyResolver())
                .requireIssuer(ISSUER)
                .build();
    }

    @Override
    public Authentication authorize(JWTAuthenticationCredentials request) {
        try {
            var jwt = decoder
                    .parseClaimsJws(request.getToken())
                    .getBody();

            return Authentication.ok(
                    Integer.parseInt(jwt.getAudience()),
                    (String) jwt.get(CLAIM_USERNAME),
                    (String) jwt.get(CLAIM_ROLE)
            );
        } catch (Exception e) {
            return Authentication.invalid();
        }
    }

    @Override
    public JWTLoginResponse login(LoginCommand request) throws UserNotFoundException, InvalidCredentialsException {
        DefaultLoginResponse loginData;
        try {
            loginData = db.login(request);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Throwable e) {
            throw new InvalidCredentialsException(e);
        }

        return new JWTLoginResponse(
                createAccessToken(loginData),
                createRefreshToken(loginData)
        );
    }

    @Override
    public JWTLoginResponse register(RegisterCommand command) throws UserExistsException, InvalidCredentialsException {
        DefaultLoginResponse loginData;
        try {
            loginData = db.register(command);
        } catch (UserExistsException e) {
            throw e;
        } catch (Throwable e) {
            throw new InvalidCredentialsException(e);
        }

        return new JWTLoginResponse(
                createAccessToken(loginData),
                createRefreshToken(loginData)
        );
    }

    protected String createAccessToken(DefaultLoginResponse userData) {
        var exp = Calendar.getInstance();
        exp.add(Calendar.MINUTE, 10);
        var now = new Date();

        return Jwts.builder()
                .setHeader(Map.of("kid", KID_ACCESS))
                .setAudience(String.valueOf(userData.getUserId()))
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(exp.getTime())
                .setId(UUID.randomUUID().toString())
                .claim(CLAIM_USERNAME, userData.getUsername())
                .claim(CLAIM_ROLE, userData.getRole())
                .setIssuer(ISSUER)
                .signWith(keyProvider.resolveSigningKey(KID_ACCESS))
                .compact();
    }

    protected String createRefreshToken(DefaultLoginResponse userData) {
        var now = Instant.now();
        return "test";
    }
}
