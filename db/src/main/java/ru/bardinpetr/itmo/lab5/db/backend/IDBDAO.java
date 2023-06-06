package ru.bardinpetr.itmo.lab5.db.backend;

/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public interface IDBDAO<V, T> extends IDBInsertDAO<V, T>, IDBReadDAO<V, T>, IDBUpdateDAO<V, T>, IDBDeleteDAO<V, T> {
}
