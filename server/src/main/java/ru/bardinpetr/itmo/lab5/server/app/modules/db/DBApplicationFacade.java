package ru.bardinpetr.itmo.lab5.server.app.modules.db;

import ru.bardinpetr.itmo.lab5.db.frontend.adapters.sync.SyncDAO;
import ru.bardinpetr.itmo.lab5.network.app.server.AbstractApplication;
import ru.bardinpetr.itmo.lab5.network.app.server.handlers.impl.AuthenticatedFilter;
import ru.bardinpetr.itmo.lab5.network.app.server.special.impl.FilteredApplication;
import ru.bardinpetr.itmo.lab5.server.db.dao.WorkersDBTableProvider;
import ru.bardinpetr.itmo.lab5.server.db.factories.WorkersDAOFactory;

public class DBApplicationFacade {

    public static AbstractApplication create(WorkersDBTableProvider tableProvider) {
        var workersDB = new WorkersDAOFactory(tableProvider).createDB();
        var syncDB = new SyncDAO<>(workersDB);

        return new FilteredApplication(
                new DBApplication(syncDB),
                AuthenticatedFilter.getInstance()
        );
    }
}
