package ru.bardinpetr.itmo.lab5.db.frontend.adapters.sync;

import ru.bardinpetr.itmo.lab5.db.frontend.dao.ICollectionDAO;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

/**
 * DAO decorator for access synchronization
 *
 * @param <K> Type of primary key
 * @param <V> Entity type
 */
public class SyncDAO<K, V extends IKeyedEntity<K>> implements ICollectionDAO<K, V> {
    private final ICollectionDAO<K, V> decoratee;
    private final ReentrantReadWriteLock.WriteLock writeLock;
    private final ReentrantReadWriteLock.ReadLock readLock;

    public SyncDAO(ICollectionDAO<K, V> decoratee) {
        this.decoratee = decoratee;

        var lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    /**
     * Clear collection
     */
    @Override
    public void clear() {
        try {
            writeLock.lock();
            decoratee.clear();
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Insert element into collection.
     * In this implementation always throws NotOwnedException. Use method with user parameter
     *
     * @param worker item to insert
     * @return key assigned to object
     */
    @Override
    public K add(V worker) {
        try {
            writeLock.lock();
            return decoratee.add(worker);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Replace entity with given id with new contents
     * In this implementation always throws NotOwnedException. Use method with user parameter
     *
     * @param id           entity id to update
     * @param updateWorker new data
     */
    @Override
    public boolean update(K id, V updateWorker) {
        try {
            writeLock.lock();
            return decoratee.update(id, updateWorker);
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Remove entity by id
     * In this implementation always throws NotOwnedException. Use method with user parameter
     *
     * @param id entity id
     * @return true if any element deleted
     */
    @Override
    public boolean remove(K id) {
        try {
            writeLock.lock();
            return decoratee.remove(id);
        } finally {
            writeLock.unlock();
        }
    }


    /**
     * @return collection stats and basic information
     */
    @Override
    public CollectionInfo getCollectionInfo() {
        try {
            readLock.lock();
            return decoratee.getCollectionInfo();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get entity by id
     *
     * @param id entity or null if invalid id
     * @return entity
     */
    @Override
    public V read(K id) {
        try {
            readLock.lock();
            return decoratee.read(id);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get all entities from collection
     *
     * @return list of all items
     */
    @Override
    public List<V> readAll() {
        try {
            readLock.lock();
            return decoratee.readAll();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get all entities from collection as stream
     *
     * @return list of all items
     */
    @Override
    public Stream<V> asStream() {
        try {
            readLock.lock();
            return decoratee.readAll().stream();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Check if collection has an object with this key
     *
     * @param id primary key
     * @return true if exists
     */
    @Override
    public boolean has(K id) {
        try {
            readLock.lock();
            return decoratee.has(id);
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get maximum entity in collection
     *
     * @return maximal element
     */
    @Override
    public V getMax() {
        try {
            readLock.lock();
            return decoratee.getMax();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get minimum entity in collection
     *
     * @return minimal element
     */
    @Override
    public V getMin() {
        try {
            readLock.lock();
            return decoratee.getMin();
        } finally {
            readLock.unlock();
        }
    }

    /**
     * Get next available primary key
     *
     * @return primary key could be assigned to new element
     */
    @Override
    public K nextPrimaryKey() {
        try {
            readLock.lock();
            return decoratee.nextPrimaryKey();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Organization> getOrganizations() {
        try {
            readLock.lock();
            return decoratee.getOrganizations();
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public K addOrg(Organization element) {
        try {
            writeLock.lock();
            return decoratee.addOrg(element);
        } finally {
            writeLock.unlock();
        }
    }
}
