package ru.bardinpetr.itmo.lab5.server;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.common.log.SetupJUL;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.models.data.Position;
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
import ru.bardinpetr.itmo.lab5.server.db.dto.OrganizationDTO;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;
import ru.bardinpetr.itmo.lab5.server.db.postgres.DBConnector;
import ru.bardinpetr.itmo.lab5.server.db.postgres.tables.OrganizationsDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.tables.UsersDAO;
import ru.bardinpetr.itmo.lab5.server.db.postgres.tables.WorkersDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.utils.BasicAuthProvider;
import ru.bardinpetr.itmo.lab5.server.executor.DBApplication;
import ru.bardinpetr.itmo.lab5.server.ui.ServerConsoleArgumentsParser;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
public class Main {
    public static void main2(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var argParse = new ServerConsoleArgumentsParser(args);

        var dbConnector = new DBConnector(
                argParse.getDatabaseUrl(),
                new BasicAuthProvider(argParse.getUsername(), argParse.getPassword())
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
            var organizationDB = new OrganizationsDBDAO(dbConnector);
            var workerDB = new WorkersDBDAO(dbConnector);
            var usersDB = new UsersDAO(dbConnector);

//            usersDB.insert(new AuthorizationObject("Artem", "adsa"));

            organizationDB.insert(new OrganizationDTO(
                    1,
                    "ITMO",
                    OrganizationType.COMMERCIAL
            ));

            organizationDB.insert(new OrganizationDTO(
                    1,
                    "ITMO",
                    OrganizationType.PUBLIC
            ));

            {
                var t = organizationDB.select();
                for (var i : t) {
                    System.out.println(i);
                }
            }

            var z = ZonedDateTime.of(
                    LocalDateTime.now(),
                    ZoneId.of("Australia/Brisbane")

            );


            workerDB.insert(new WorkerDTO(
                    1,
                    1,
                    1,
                    z,
                    Date.from(Instant.now()),
                    null,
                    "Petr",
                    (float) 12312,
                    new Coordinates(12, -2),
                    Position.CLEANER

            ));
            workerDB.update(4, new WorkerDTO(
                    1,
                    1,
                    1,
                    z,
                    Date.from(Instant.now()),
                    null,
                    "Sasha",
                    (float) 12312,
                    new Coordinates(12, -2),
                    Position.CLEANER

            ));


            {
                var t = workerDB.select();
                for (var i : t) {
                    System.out.println(i);
                }
            }
        } catch (DBCreateException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        SetupJUL.loadProperties(Main.class);
        var argParse = new ServerConsoleArgumentsParser(args);

        var dbConnector = new DBConnector(
                argParse.getDatabaseUrl(),
                new BasicAuthProvider(argParse.getUsername(), argParse.getPassword())
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
            var wrks = new WorkersDBDAO(dbConnector);
            System.out.println(wrks.select());
        } catch (DBCreateException e) {
            throw new RuntimeException(e);
        }

        var workersDB = new WorkersDAOFactory().createDB();

        UsersDAO usersDB = null;
        try {
            usersDB = new UsersDAO(dbConnector);
        } catch (DBCreateException e) {
            log.error("Failed to bootstrap DB", e);
            System.exit(-1);
        }

        var syncDB = SynchronizedDAOFactory.wrap(workersDB);
        var dbApplication = new DBApplication(syncDB);

        var authReceiver = new DBAuthenticationReceiver(usersDB);

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
