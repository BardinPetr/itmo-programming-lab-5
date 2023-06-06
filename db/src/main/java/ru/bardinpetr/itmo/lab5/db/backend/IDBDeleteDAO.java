package ru.bardinpetr.itmo.lab5.db.backend;


/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public interface IDBDeleteDAO<V, T> {
    boolean delete(V id);

    boolean drop();

    boolean truncate();
}
