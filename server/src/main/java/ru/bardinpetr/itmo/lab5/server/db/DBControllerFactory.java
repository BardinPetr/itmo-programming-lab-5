package ru.bardinpetr.itmo.lab5.server.db;

import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;

/**
 * Interface for DB controller factories
 */
public interface DBControllerFactory {
    /**
     * Create DBController
     *
     * @return specific DB Controller object
     * @throws DBCreateException if DB can't be created or initialized
     */
    DBController createController() throws DBCreateException;
}
