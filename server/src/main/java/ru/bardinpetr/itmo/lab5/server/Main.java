package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.network.app.server.handlers.impl.AuthenticatedFilter;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.app.AuthenticationApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.modules.auth.models.api.DefaultAPICommandAuthenticator;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.ErrorHandlingApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.FilteredApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPInputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.UDPOutputTransportApplication;
import ru.bardinpetr.itmo.lab5.network.transport.server.UDPServerFactory;
import ru.bardinpetr.itmo.lab5.server.app.WorkersDAOFactory;
import ru.bardinpetr.itmo.lab5.server.auth.DBAuthenticationReceiver;
import ru.bardinpetr.itmo.lab5.server.dao.sync.SynchronizedDAOFactory;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;
import ru.bardinpetr.itmo.lab5.server.db.postgres.tables.OrganizationsDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.utils.BasicAuthProvider;
import ru.bardinpetr.itmo.lab5.server.executor.DBApplication;
import ru.bardinpetr.itmo.lab5.server.ui.ServerConsoleArgumentsParser;

@Slf4j
public class Main {
    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var argParse = new ServerConsoleArgumentsParser(args);

        var dbConnector = new DBConnector(
                "jdbc:postgresql://localhost:5432/studs",
                new BasicAuthProvider("s367079", "aKNKcUmScdpvwhYu")
        );

        if (argParse.doBootstrap()) {
            var res = dbConnector.bootstrap(Main.class.getResourceAsStream("/db/create.sql"));
            if (res) {
                log.info("DB bootstrapped successfully");
            } else {
                log.error("Failed to bootstrap DB");
                return;
            }
        }

        try {
            var orgs = new OrganizationsDBDAO(dbConnector);

        } catch (DBCreateException e) {
            throw new RuntimeException(e);
        }

        var workersDB = new WorkersDAOFactory().createDB();
        var syncDB = SynchronizedDAOFactory.wrap(workersDB);
        var dbApplication = new DBApplication(syncDB);

        var authReceiver = new DBAuthenticationReceiver();

        var transport = UDPServerFactory.create(argParse.getPort());

        var mainApp = new UDPInputTransportApplication(transport);
        mainApp
                .chain(new UDPOutputTransportApplication(transport))
                .chain(new AuthenticationApplication<>(DefaultAPICommandAuthenticator.getInstance(), authReceiver))
                .chain(new FilteredApplication(
                        dbApplication,
                        AuthenticatedFilter.getInstance()
                ))
                .chain(new ErrorHandlingApplication());

        mainApp.start();
    }
}
