package ru.bardinpetr.itmo.lab5.server.db.factories;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.server.db.dao.WorkersDBTableProvider;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.IWorkerCollectionDAO;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.PGWorkerStorageBackend;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.WorkersCachedCollection;

@Slf4j
public class WorkersDAOFactory {
    private final WorkersDBTableProvider tableProvider;

    public WorkersDAOFactory(WorkersDBTableProvider tableProvider) {
        this.tableProvider = tableProvider;
    }

    public IWorkerCollectionDAO createDB() {
        log.info("Initializing Workers DB");

        return new WorkersCachedCollection(new PGWorkerStorageBackend(tableProvider));
    }
}
