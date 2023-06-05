package ru.bardinpetr.itmo.lab5.db.backend;


/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public interface IDBInsertDAO<V, T> {
    V insert(T data);
}
