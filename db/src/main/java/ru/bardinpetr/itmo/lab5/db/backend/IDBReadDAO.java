package ru.bardinpetr.itmo.lab5.db.backend;

import java.util.Collection;


/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public interface IDBReadDAO<V, T> {

    Collection<T> select();

    T select(V id);

    String getTableName();
}
