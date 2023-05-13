package ru.bardinpetr.itmo.lab5.server.db.factories;

import ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached.CachedCollectionController;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.db.dao.ctrl.CachedDBWorkersDAO;

public class WorkerCollectionDAOFactory {

    /**
     * Create Workers DAO for FileDB controller
     *
     * @param dbController DB controller to use
     * @return Worker Collection DAO
     */
    public ICollectionDAO<Integer, Worker> createDAO(CachedCollectionController<WorkerCollection> dbController) {
        return new CachedDBWorkersDAO(dbController);
    }
}
