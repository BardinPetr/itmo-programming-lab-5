package ru.bardinpetr.itmo.lab5.server.dao.filedb;

import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;
import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionFilteredDAO;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Abstract Data Access Object for operating with Set-based collection of entries with primary key.
 * Implements base CRUD operations.
 * Uses FileDB backend.
 *
 * @param <K> Key for identifying objects
 * @param <V> Objects in collection
 */
public class FileDBDAO<K, V extends IKeyedEntity<K>> implements ICollectionFilteredDAO<K, V> {
    private final FileDBController<? extends ISetCollection<K, V>> controller;

    public FileDBDAO(FileDBController<? extends ISetCollection<K, V>> controller) {
        this.controller = controller;
    }

    private Stream<V> asStream() {
        return controller.data().stream();
    }

    @Override
    public void clear() {
        controller.data().clear();
    }

    @Override
    public void save() {
        controller.store();
    }

    @Override
    public V getMax() {
        return controller.data().last();
    }

    @Override
    public V getMin() {
        return controller.data().first();
    }

    @Override
    public CollectionInfo getCollectionInfo() {
        return new CollectionInfo();
    }

    @Override
    public V read(K id) {
        return asStream()
                .filter(data -> data.getPrimaryKey() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<V> readAll() {
        return asStream().toList();
    }

    @Override
    public List<V> readAll(Comparator<V> order) {
        return asStream()
                .sorted(order).toList();
    }

    @Override
    public void add(V worker) {
        controller.data().add(worker);
    }

    public void update(K id, V updateObject) {
        remove(id);
        add(updateObject);
    }

    @Override
    public void remove(K id) {
        controller.data().removeIf(data -> data.getPrimaryKey() == id);
    }
}
