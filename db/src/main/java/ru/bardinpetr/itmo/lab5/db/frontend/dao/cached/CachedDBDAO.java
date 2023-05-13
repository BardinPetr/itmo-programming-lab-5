package ru.bardinpetr.itmo.lab5.db.frontend.dao.cached;

import ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached.CachedCollectionController;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;
import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/**
 * Data Access Object for operating with Set-based collection of entries with primary key.
 * Implements base CRUD operations.
 * Uses FileDB backend.
 *
 * @param <K> Key for identifying objects
 * @param <V> Objects in collection
 */
public class CachedDBDAO<K extends Comparable<K>, V extends IKeyedEntity<K>> implements ICollectionDAO<K, V> {
    private final CachedCollectionController<? extends ISetCollection<K, V>> controller;

    public CachedDBDAO(CachedCollectionController<? extends ISetCollection<K, V>> controller) {
        this.controller = controller;
    }

    public Stream<V> asStream() {
        return controller.collection().stream();
    }

    @Override
    public void clear() {
        controller.collection().clear();
    }

    @Override
    public void save() {
        controller.store();
    }

    @Override
    public V getMax() {
        try {
            return controller.collection().last();
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    @Override
    public V getMin() {
        try {
            return controller.collection().first();
        } catch (NoSuchElementException ignored) {
            return null;
        }
    }

    @Override
    public CollectionInfo getCollectionInfo() {
        return new CollectionInfo(WorkerCollection.class.getSimpleName(), Worker.class.getName(), controller.info().getInitializationDate(), controller.collection().size());
    }

    @Override
    public V read(K id) {
        return asStream().filter(data -> data.getPrimaryKey().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<V> readAll() {
        return asStream().toList();
    }

    @Override
    public List<V> readAll(Comparator<V> order) {
        return asStream().sorted(order).toList();
    }

    @Override
    public boolean has(K id) {
        return asStream().anyMatch(i -> i.getPrimaryKey().equals(id));
    }

    @Override
    public K add(V item) {
        controller.collection().add(item);
        return item.getPrimaryKey();
    }

    public void update(K id, V updateObject) {
        if (!has(id)) throw new IndexOutOfBoundsException("no object with such id");
        remove(id);
        controller.collection().add(updateObject);
    }

    @Override
    public boolean remove(K id) {
        return controller.collection().removeIf(data -> data.getPrimaryKey().equals(id));
    }

    @Override
    public K nextPrimaryKey() {
        return null;
    }
}
