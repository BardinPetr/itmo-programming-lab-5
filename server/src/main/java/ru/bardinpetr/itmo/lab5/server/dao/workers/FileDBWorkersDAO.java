package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.filedb.FileDBDAO;
import ru.bardinpetr.itmo.lab5.server.filedb.FileDBController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Implementation of collection DAO for workers database.
 */
public class FileDBWorkersDAO extends FileDBDAO<Integer, Worker> implements IWorkerCollectionDAO {

    public FileDBWorkersDAO(FileDBController<WorkerCollection> controller) {
        super(controller);
    }

    /**
     * @return next integer for max id in db
     */
    @Override
    public Integer nextPrimaryKey() {
        return getMapped(Worker::getPrimaryKey).stream().max(Integer::compareTo).orElse(0) + 1;
    }

    @Override
    public Integer add(Worker item) {
        var key = nextPrimaryKey();
        super.add(item.withId(key));
        return key;
    }

    @Override
    public CollectionInfo getCollectionInfo() {
        var info = super.getCollectionInfo();
        var first = readAll()
                .stream()
                .map(Worker::getCreationDate)
                .sorted()
                .findFirst().orElse(LocalDateTime.now());
        info.setInitializationDate(Date.from(first.atZone(ZoneId.systemDefault()).toInstant()));
        return info;
    }
}
