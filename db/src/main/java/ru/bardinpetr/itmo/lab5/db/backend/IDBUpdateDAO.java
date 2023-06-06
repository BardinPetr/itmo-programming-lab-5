package ru.bardinpetr.itmo.lab5.db.backend;


/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public interface IDBUpdateDAO<V, T> {
    boolean update(V id, T newData);
}
