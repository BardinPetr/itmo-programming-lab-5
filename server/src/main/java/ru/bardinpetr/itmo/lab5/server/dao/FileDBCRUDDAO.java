package ru.bardinpetr.itmo.lab5.server.dao;

import ru.bardinpetr.itmo.lab5.models.data.collection.IIdentifiableEntry;
import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

/**
 * Abstract Data Access Object for operating with Set-based collection of entries with primary key.
 * Implements base CRUD operations.
 * Uses FileDB backend.
 *
 * @param <K> Key for identifying objects
 * @param <V> Objects in collection
 */
public abstract class FileDBCRUDDAO<K, V extends IIdentifiableEntry<K>> {
    private final FileDBController<? extends ISetCollection<K, V>> controller;

    public FileDBCRUDDAO(FileDBController<? extends ISetCollection<K, V>> controller) {
        this.controller = controller;
    }

    public void create(V object) {
        controller.collection.add(object);
    }

    public V read(K id) {
        return controller.collection
                .stream()
                .filter(data -> data.getPrimaryKey() == id)
                .findFirst()
                .orElse(null);
    }

    public ISetCollection<K, V> readAll() {
        return controller.collection;
    }

    public void remove(K id) {
        controller.collection.removeIf(data -> data.getPrimaryKey() == id);
    }

    public void update(K id, V updateObject) {
        remove(id);
        create(updateObject);
    }
}
