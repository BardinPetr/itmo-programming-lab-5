package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionDAO;

/**
 * DAO for accessing Workers collection
 */
public interface IWorkerCollectionDAO extends ICollectionDAO<Integer, Worker> {
}
