package ru.bardinpetr.itmo.lab5.server.db.postgres;

import org.postgresql.ds.PGSimpleDataSource;
import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;

import java.sql.Connection;
import java.util.Collection;

/**
 * @param <V> primary key type
 * @param <T> row DTO type
 */
public abstract class BaseDBDAO<V, T> {

    protected final String tableName;
    protected final DBConnector connector;
    protected final PGSimpleDataSource datasource;
    protected final Connection connection;

    public BaseDBDAO(DBConnector connector, String tableName) throws DBCreateException {
        this.connector = connector;
        this.tableName = tableName;

        try {
            this.datasource = connector.getDataSource();
            this.connection = datasource.getConnection();
        } catch (Exception e) {
            throw new DBCreateException(e);
        }
    }

    public abstract boolean createTable();

    public abstract boolean insert(T data);

    public abstract boolean update(V id, T newData);

    public abstract Collection<T> select();

    public abstract T select(V id);

    public abstract boolean delete(V id);
}
