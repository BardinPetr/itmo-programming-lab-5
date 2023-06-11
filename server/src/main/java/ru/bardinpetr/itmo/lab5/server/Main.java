package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.app.server.handlers.impl.AuthenticatedFilter;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.FilteredApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.server.app.modules.auth.AuthAppFacade;
import ru.bardinpetr.itmo.lab5.server.app.modules.db.DBApplicationFacade;
import ru.bardinpetr.itmo.lab5.server.app.modules.events.EventFacade;
import ru.bardinpetr.itmo.lab5.server.app.ui.ServerConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.server.db.factories.TableProviderFactory;
//--port 5000 -d jdbc:postgresql://localhost:5432/studs -u s367079 -p aKNKcUmScdpvwhYu
@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var consoleArgs = new ServerConsoleArgumentsParser(args);

        var eventApp = EventFacade.createApp();

        var tableProvider = TableProviderFactory.create(
                consoleArgs.getDatabaseUrl(),
                consoleArgs.getUsername(), consoleArgs.getPassword(),
                consoleArgs.doBootstrap()
        );

        var udpServer = UDPServerFactory.create(consoleArgs.getPort());
        var mainApp = new UDPInputTransportApplication(udpServer);
        mainApp
                .chain(new UDPOutputTransportApplication(udpServer))
                .chain(new AuthAppFacade().create(tableProvider))
                .chain(DBApplicationFacade.create(tableProvider))
                .chain(new FilteredApplication(
                        eventApp, AuthenticatedFilter.getInstance()
                ))
                .chain(new ErrorHandlingApplication());

        mainApp.start();
    }
}
