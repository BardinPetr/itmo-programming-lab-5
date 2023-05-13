package ru.bardinpetr.itmo.lab5.db.backend.impl.postgres;

import lombok.Getter;
import ru.bardinpetr.itmo.lab5.db.backend.DBStorageBackend;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;
import ru.bardinpetr.itmo.lab5.models.data.collection.ISetCollection;

public class PostgresStorageBackend<V, T extends IKeyedEntity<V>> implements DBStorageBackend<ISetCollection<V, T>> {

    @Getter
    private final BasePGDAO<V, T> dao;

    public PostgresStorageBackend(BasePGDAO<V, T> dao) {
        this.dao = dao;
    }

    @Override
    public void storeCollection(ISetCollection<V, T> data) {
    }

    @Override
    public ISetCollection<V, T> loadCollection() {
        return dao.select();
    }

    @Override
    public void clearCollection() {
        dao.truncate();
    }

    @Override
    public CollectionInfo getInfo() {
        return null;
    }
}
