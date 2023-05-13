package ru.bardinpetr.itmo.lab5.server.db.dao;

import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;

import java.util.List;
import java.util.stream.Stream;

public class CollectionCache implements ICollectionDAO<Integer, Worker> {

    private final DBTableProvider tableProvider;

    public CollectionCache(DBTableProvider tableProvider) {
        this.tableProvider = tableProvider;
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
    public Integer add(Worker worker) {
        return null;
    }

    @Override
    public void update(Integer id, Worker updateWorker) {

    }

    @Override
    public boolean remove(Integer id) {
        return false;
    }

    @Override
    public void clear() {

    }
}
