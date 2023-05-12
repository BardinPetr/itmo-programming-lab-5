package ru.bardinpetr.itmo.lab5.server.dao.workers;

import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;
import ru.bardinpetr.itmo.lab5.server.dao.filedb.FileDBDAO;
import ru.bardinpetr.itmo.lab5.server.db.filedb.RAMCollectionController;

import java.util.Random;

/**
 * Implementation of collection DAO for workers database.
 */
public class FileDBWorkersDAO extends FileDBDAO<Integer, Worker> implements IWorkerCollectionDAO {

    public FileDBWorkersDAO(RAMCollectionController<WorkerCollection> controller) {
        super(controller);
    }

    /**
     * @return next integer for max id in db
     */
    @Override
    public Integer nextPrimaryKey() {
        return new Random().nextInt(0, Integer.MAX_VALUE);
//        return getAllMapped(Worker::getPrimaryKey).stream().max(Integer::compareTo).orElse(0) + 1;
    }

    @Override
    public Integer add(Worker item) {
        var key = nextPrimaryKey();
        super.add(item.withId(key));
        return key;
    }

    @Override
    public void update(Integer id, Worker updateObject) {
        var cur = read(id);
        if (cur == null)
            throw new IndexOutOfBoundsException("no object with such id");
        super.update(
                id,
                updateObject
                        .withId(id)
                        .withCreationDate(cur.getCreationDate())
        );
    }
}
