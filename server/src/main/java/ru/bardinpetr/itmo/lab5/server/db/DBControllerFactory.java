package ru.bardinpetr.itmo.lab5.server.db;

import ru.bardinpetr.itmo.lab5.server.db.errors.DBCreateException;

public interface DBControllerFactory {
    DBController createController() throws DBCreateException;
}
