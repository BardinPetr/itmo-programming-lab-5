package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.AuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTAuthenticationCredentials;

import java.util.Map;

/**
 * Service for requesting user to authenticate before running main application
 */
public class LoginPage<T extends AuthenticationCredentials> {
    private final Runnable callback;
    private final UIReceiver ui;
    private final ICredentialsStorage<JWTAuthenticationCredentials> storage;
    private final APIClientConnector api;
    private final JWTRegisterLocalCommand registerCmd;
    private final JWTLoginLocalCommand loginCmd;

    public LoginPage(APIClientConnector api, UIReceiver ui, ICredentialsStorage<JWTAuthenticationCredentials> credentialsStorage, Runnable onLogin) {
        this.callback = onLogin;
        this.ui = ui;
        this.api = api;

        this.storage = credentialsStorage;

        this.loginCmd = new JWTLoginLocalCommand(api, ui, storage);
        this.registerCmd = new JWTRegisterLocalCommand(api, ui, storage);

//        try {
//            this.loginCmd = (L) initAuthCommand(loginCmd);
//            this.registerCmd = (R) initAuthCommand(registerCmd);
//        } catch (Throwable e) {
//            System.err.printf("Unable to initialize auth commands:\n%s\n", e);
//            System.exit(1);
//        }
    }

//    private AuthLocalCommand<T> initAuthCommand(Class<JWTRegisterLocalCommand> cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        return cls
//                .getConstructor(APIClientConnector.class, UIReceiver.class, ICredentialsStorage.class)
//                .newInstance(api, ui, storage);
//    }

    public void run() {
        if (storage.getCredentials() != null) {
            callback.run();
            return;
        }

        AuthLocalCommand<JWTAuthenticationCredentials> selectedCommand;
        while (true) {
            ui.display("You need to authenticate. Type L for login or R for registration");
            var req = ui.nextLine();
            if (req.equals("R")) {
                selectedCommand = registerCmd;
                break;
            }
            if (req.equals("L")) {
                selectedCommand = loginCmd;
                break;
            }
            ui.display("Invalid choice");
        }

        var res = selectedCommand.execute(null, Map.of());
        if (res.isSuccess()) {
            callback.run();
        } else {
            run();
        }
    }
}
