package ru.bardinpetr.itmo.lab5.server.db.dao;

import ru.bardinpetr.itmo.lab5.db.backend.impl.postgres.PGDBConnector;

public abstract class DBTableProvider {
    protected final PGDBConnector PGDBConnector;

    public DBTableProvider(PGDBConnector PGDBConnector) {
        this.PGDBConnector = PGDBConnector;
    }
}
