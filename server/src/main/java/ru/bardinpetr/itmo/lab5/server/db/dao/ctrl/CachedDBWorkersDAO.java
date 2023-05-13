package ru.bardinpetr.itmo.lab5.server.db.dao.ctrl;

import ru.bardinpetr.itmo.lab5.db.frontend.controllers.cached.CachedCollectionController;
import ru.bardinpetr.itmo.lab5.db.frontend.dao.cached.CachedDBDAO;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.collection.WorkerCollection;

import java.util.Random;

/**
 * Implementation of collection DAO for workers database.
 */
public class CachedDBWorkersDAO extends CachedDBDAO<Integer, Worker> implements IWorkerCollectionDAO {

    public CachedDBWorkersDAO(CachedCollectionController<WorkerCollection> controller) {
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
