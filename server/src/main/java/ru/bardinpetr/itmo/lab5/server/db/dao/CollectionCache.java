package ru.bardinpetr.itmo.lab5.server.db.dao;

import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionReadDAO;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionWriteDAO;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.db.dto.WorkerDTO;

import java.util.List;
import java.util.stream.Stream;

public class CollectionCache implements ICollectionReadDAO<Integer, Worker>, ICollectionWriteDAO<Integer, WorkerDTO> {

    private final PGWorkerStorageBackend backend;
    private WorkerCollection collection;

    public CollectionCache(PGWorkerStorageBackend backend) {
        this.backend = backend;
    }

    @Override
    public CollectionInfo getCollectionInfo() {
        return null;
    }

    @Override
    public Worker read(Integer id) {
        return null;
    }

    @Override
    public List<Worker> readAll() {
        return null;
    }

    @Override
    public Stream<Worker> asStream() {
        return null;
    }

    @Override
    public boolean has(Integer id) {
        return false;
    }

    @Override
    public Worker getMax() {
        return null;
    }

    @Override
    public Worker getMin() {
        return null;
    }

    @Override
    public Integer nextPrimaryKey() {
        return null;
    }

    @Override
    public Integer add(WorkerDTO worker) {
        return null;
    }

    @Override
    public void update(Integer id, WorkerDTO updateWorker) {

    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public void clear() {

    }
}
