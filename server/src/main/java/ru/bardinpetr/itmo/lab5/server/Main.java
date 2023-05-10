package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.AuthenticationApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.DefaultAPICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.server.api.ExecutorAdapterApplication;
import ru.bardinpetr.itmo.lab5.server.auth.DBAuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.server.executor.DBExecutor;
import ru.bardinpetr.itmo.lab5.server.ui.ServerConsoleArgumentsParser;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);

        var argParse = new ServerConsoleArgumentsParser(args);

        var dbExecutor = new DBExecutor(argParse.getDatabasePath());
        var dbApp = new ExecutorAdapterApplication(dbExecutor);

        var transport = UDPServerFactory.create(
                argParse.getPort()
        );

        var authReceiver = new DBAuthenticationReceiver();

        var mainApp = new UDPInputTransportApplication(transport);

        mainApp.use(new UDPOutputTransportApplication(transport));
        mainApp.use(new AuthenticationApplication<>(DefaultAPICommandAuthenticator.getInstance(), authReceiver));
        mainApp.use(dbApp);
        mainApp.use(new ErrorHandlingApplication());

        mainApp.start();
    }
}
