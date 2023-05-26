package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.server.app.modules.auth.AuthAppFacade;
import ru.bardinpetr.itmo.lab5.server.app.modules.db.DBApplicationFacade;
import ru.bardinpetr.itmo.lab5.server.app.ui.ServerConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.server.db.factories.TableProviderFactory;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var consoleArgs = new ServerConsoleArgumentsParser(args);

        var tableProvider = TableProviderFactory.create(
                consoleArgs.getDatabaseUrl(),
                consoleArgs.getUsername(), consoleArgs.getPassword(),
                consoleArgs.doBootstrap()
        );

        var auth = new AuthAppFacade();
        auth.setDebug(false);

        var udpServer = UDPServerFactory.create(consoleArgs.getPort());
        var mainApp = new UDPInputTransportApplication(udpServer);
        mainApp
                .chain(new UDPOutputTransportApplication(udpServer))
                .chain(auth.create(tableProvider))
                .chain(DBApplicationFacade.create(tableProvider))
                .chain(new ErrorHandlingApplication());

        mainApp.start();
    }
}
