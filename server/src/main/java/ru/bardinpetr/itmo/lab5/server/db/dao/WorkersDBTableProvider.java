package ru.bardinpetr.itmo.lab5.server.db.dao;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;
import ru.bardinpetr.itmo.lab5.server.Main;
import ru.bardinpetr.itmo.lab5.server.app.modules.events.DBEventLoggerProxy;
import ru.bardinpetr.itmo.lab5.server.app.utils.ServiceProvider;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.OrganizationsDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.UsersDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.WorkersDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.impl.OrganizationsPGDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.impl.UsersPGDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.tables.impl.WorkersPGDAO;

@Slf4j
public class WorkersDBTableProvider extends DBTableProvider {

    @Getter
    private final OrganizationsDAO organizations;
    @Getter
    private final WorkersDAO workers;
    @Getter
    private final UsersDAO users;


    public WorkersDBTableProvider(PGDBConnector connector) {
        super(connector);

        var loggerProxyFactory = (DBEventLoggerProxy) ServiceProvider.getInstance().get(DBEventLoggerProxy.class, "loggerProxyFactory");

        try {
            organizations = loggerProxyFactory.wrap(new OrganizationsPGDAO(PGDBConnector), OrganizationsDAO.class);
            workers = loggerProxyFactory.wrap(new WorkersPGDAO(PGDBConnector), WorkersDAO.class);
            users = loggerProxyFactory.wrap(new UsersPGDAO(PGDBConnector), UsersDAO.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void bootstrap() {
        var res = PGDBConnector.bootstrap(Main.class.getResourceAsStream("/db/create.sql"));
        if (res) {
            log.info("DB bootstrapped successfully");
        } else {
            log.error("Failed to bootstrap DB");
            System.exit(1);
        }
    }
}
