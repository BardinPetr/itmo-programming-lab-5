package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.filedb.FileDBDAO;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class FileDBWorkersDAO extends FileDBDAO<Long, Worker> implements IWorkerCollectionDAO {

    public FileDBWorkersDAO(FileDBController<WorkerCollection> controller) {
        super(controller);
    }
}
