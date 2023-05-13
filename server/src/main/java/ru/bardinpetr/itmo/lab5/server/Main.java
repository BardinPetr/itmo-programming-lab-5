package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.db.auth.BasicAuthProvider;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;
import ru.bardinpetr.itmo.lab5.db.frontend.adapters.sync.SynchronizedDAOFactory;
import ru.bardinpetr.itmo.lab5.network.app.server.handlers.impl.AuthenticatedFilter;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.AuthenticationApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.DefaultAPICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.FilteredApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.server.app.modules.db.DBApplication;
import ru.bardinpetr.itmo.lab5.server.app.ui.ServerConsoleArgumentsParser;
import ru.bardinpetr.itmo.lab5.server.auth.recv.DBAuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.server.db.dao.DBTableProvider;
import ru.bardinpetr.itmo.lab5.server.db.factories.WorkersDAOFactory;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var consoleArgs = new ServerConsoleArgumentsParser(args);

        var tableProvider = new DBTableProvider(
                new PGDBConnector(
                        consoleArgs.getDatabaseUrl(),
                        new BasicAuthProvider(consoleArgs.getUsername(), consoleArgs.getPassword())
                )
        );

        // drop tables and recreate db
        if (consoleArgs.doBootstrap()) tableProvider.bootstrap();

        var workersDB = new WorkersDAOFactory(tableProvider).createDB();
        var syncDB = SynchronizedDAOFactory.wrap(workersDB);
        var dbApplication = new DBApplication(syncDB);

        var authReceiver = new DBAuthenticationReceiver(tableProvider.getUsers());

        var udpServer = UDPServerFactory.create(consoleArgs.getPort());

        var mainApp = new UDPInputTransportApplication(udpServer);
        mainApp
                .chain(new UDPOutputTransportApplication(udpServer))
                .chain(new AuthenticationApplication<>(
                        DefaultAPICommandAuthenticator.getInstance(),
                        authReceiver)
                )
                .chain(new FilteredApplication(
                        dbApplication,
                        AuthenticatedFilter.getInstance()
                ))
                .chain(new ErrorHandlingApplication());

        mainApp.start();
    }
}
