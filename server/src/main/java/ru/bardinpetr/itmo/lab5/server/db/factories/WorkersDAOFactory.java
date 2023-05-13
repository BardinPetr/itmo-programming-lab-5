package ru.bardinpetr.itmo.lab5.server.db.factories;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.server.db.dao.DBTableProvider;
import ru.bardinpetr.itmo.lab5.server.db.dao.PGWorkerStorageBackend;
import ru.bardinpetr.itmo.lab5.server.db.dao.WorkersCachedCollection;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.IWorkerCollectionDAO;

@Slf4j
public class WorkersDAOFactory {
    private final DBTableProvider tableProvider;

    public WorkersDAOFactory(DBTableProvider tableProvider) {
        this.tableProvider = tableProvider;
    }

    public IWorkerCollectionDAO createDB() {
        log.info("Initializing Workers DB");

        return new WorkersCachedCollection(new PGWorkerStorageBackend(tableProvider));
    }
}
