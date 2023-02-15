package ru.bardinpetr.itmo.lab5.server.dao;

import ru.bardinpetr.itmo.lab5.models.data.collection.IIdentifiableEntry;
import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public abstract class CRUDDAO<K, V extends IIdentifiableEntry<K>> {
    private final FileDBController<? extends ISetCollection<K, V>> controller;

    public CRUDDAO(FileDBController<? extends ISetCollection<K, V>> controller) {
        this.controller = controller;
    }

    public void create(V object) {
        controller.collection.add(object);
    }

    public V read(K id) {
        return controller.collection
                .stream()
                .filter(data -> data.getKey() == id)
                .findFirst()
                .orElse(null);
    }

    public ISetCollection<K, V> readAll() {
        return controller.collection;
    }

    public void remove(K id) {
        controller.collection.removeIf(data -> data.getKey() == id);
    }

    public void update(K id, V updateObject) {
        remove(id);
        create(updateObject);
    }
}
