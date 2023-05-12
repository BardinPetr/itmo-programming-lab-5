package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.server.db.filedb.RAMCollectionController;

public class WorkerCollectionDAOFactory {

    /**
     * Create Workers DAO for FileDB controller
     *
     * @param dbController DB controller to use
     * @return Worker Collection DAO
     */
    public ICollectionDAO<Integer, Worker> createDAO(RAMCollectionController<WorkerCollection> dbController) {
        return new FileDBWorkersDAO(dbController);
    }
}
