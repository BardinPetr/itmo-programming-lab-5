package ru.bardinpetr.itmo.lab5.mainclient.local.controller.commands.auth;

import ru.bardinpetr.itmo.lab5.client.api.APIClientConnector;
import ru.bardinpetr.itmo.lab5.client.api.auth.ICredentialsStorage;
import ru.bardinpetr.itmo.lab5.client.controller.common.APIUILocalCommand;
import ru.bardinpetr.itmo.lab5.client.ui.interfaces.UIReceiver;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;

import java.util.Map;

/**
 * Service for requesting user to authenticate before running main application
 */
public class LoginPage {
    private final Runnable callback;
    private final UIReceiver ui;
    private final AuthLocalCommand registerCmd;
    private final AuthLocalCommand loginCmd;

    public LoginPage(APIClientConnector api, UIReceiver ui, ICredentialsStorage<DefaultAuthenticationCredentials> credentialsStorage, Runnable onLogin) {
        this.callback = onLogin;
        this.ui = ui;

        this.loginCmd = new LoginLocalCommand(api, ui, credentialsStorage);
        this.registerCmd = new RegisterLocalCommand(api, ui, credentialsStorage);
    }

    public void run() {
        APIUILocalCommand selectedCommand;
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
