package ru.bardinpetr.itmo.lab5.server.db.dao;

import ru.bardinpetr.itmo.lab5.db.backend.DBStorageBackend;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;

public class PGWorkerStorageBackend implements DBStorageBackend<WorkerCollection> {

    private final DBTableProvider tableProvider;

    public PGWorkerStorageBackend(DBTableProvider tableProvider) {
        this.tableProvider = tableProvider;
    }

    @Override
    public void storeCollection(WorkerCollection data) {
    }

    @Override
    public WorkerCollection loadCollection() {
        return null;
    }

    @Override
    public void clearCollection() {

    }

    @Override
    public CollectionInfo getInfo() {
        return null;
    }

    public DBTableProvider getTableProvider() {
        return tableProvider;
    }
}
