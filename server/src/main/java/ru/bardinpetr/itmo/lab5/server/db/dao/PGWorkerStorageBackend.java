package ru.bardinpetr.itmo.lab5.server.db.dao;

import ru.bardinpetr.itmo.lab5.db.backend.DBStorageBackend;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;

public class PGWorkerStorageBackend implements DBStorageBackend<WorkerCollection> {

    private final DBTableProvider tableProvider;

    public PGWorkerStorageBackend(DBTableProvider tableProvider) {
        this.tableProvider = tableProvider;
    }

    @Override
    public void storeCollection(WorkerCollection data) {//later
    }

    @Override
    public WorkerCollection loadCollection() {
        WorkerCollection collection = new WorkerCollection();
        var t = tableProvider.getWorkers().select();
        for (var worker : t) {
            var org = tableProvider.getOrganizations().select(worker.organizationId());
            collection.add(new Worker(
                    worker.id(),
                    worker.creationDate(),
                    worker.ownerId(),
                    worker.name(),
                    worker.salary(),
                    worker.startDate(),
                    worker.endDate(),
                    worker.coordinates(),
                    new Organization(
                            org.fullName(),
                            org.type()
                    ),
                    worker.position()
            ));
            return collection;
        }

        return null;
    }

    @Override
    public void clearCollection() {
        tableProvider.getOrganizations().truncate();
        tableProvider.getWorkers().truncate();
    }

    @Override
    public CollectionInfo getInfo() {
        return new CollectionInfo(
                null,
                null,
                tableProvider.getOrganizations().,
                null
        );
    }

    public DBTableProvider getTableProvider() {
        return tableProvider;
    }
}
