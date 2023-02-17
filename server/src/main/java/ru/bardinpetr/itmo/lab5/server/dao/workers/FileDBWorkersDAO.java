package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.filedb.FileDBDAO;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

public class FileDBWorkersDAO extends FileDBDAO<Long, Worker> implements IWorkerCollectionDAO {

    public FileDBWorkersDAO(FileDBController<WorkerCollection> controller) {
        super(controller);
    }

    @Override
    public Long nextPrimaryKey() {
        return getMapped(Worker::getPrimaryKey).stream().max(Long::compareTo).orElse(0L) + 1;
    }

    @Override
    public Long add(Worker item) {
        var key = nextPrimaryKey();
        super.add(item.withId(key));
        return key;
    }
}
