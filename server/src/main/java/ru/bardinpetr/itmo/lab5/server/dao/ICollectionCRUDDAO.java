package ru.bardinpetr.itmo.lab5.server.dao;

import ru.bardinpetr.itmo.lab5.models.data.collection.CollectionInfo;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * DAO for CRUD operations.
 *
 * @param <K> Type of primary key
 * @param <V> Entity type
 */
public interface ICollectionCRUDDAO<K, V extends IKeyedEntity<K>> {
    /**
     * @return collection stats and basic information
     */
    CollectionInfo getCollectionInfo();

    /**
     * Get entity by id
     *
     * @param id entity or null if invalid id
     * @return entity
     */
    V read(K id);

    /**
     * Get all entities from collection
     *
     * @return list of all items
     */
    List<V> readAll();

    /**
     * Get all entities from collection as stream
     *
     * @return list of all items
     */
    Stream<V> asStream();

    /**
     * Get all entities from collection in order using comparator
     *
     * @param order comparator to apply
     * @return sorted list of all entities
     */
    default List<V> readAll(Comparator<V> order) {
        return readAll().stream().sorted(order).toList();
    }

    /**
     * Check if collection has an object with this key
     *
     * @param id primary key
     * @return true if exists
     */
    boolean has(K id);

    /**
     * Insert element into collection
     *
     * @param worker item to insert
     * @return key assigned to object
     */
    K add(V worker);

    /**
     * Replace entity with given id with new contents
     *
     * @param id           entity id to update
     * @param updateWorker new data
     */
    void update(K id, V updateWorker);

    /**
     * Remove entity by id
     *
     * @param id entity id
     * @return true if any element deleted
     */
    boolean remove(K id);

    /**
     * Clear collection
     */
    void clear();

    /**
     * Store collection to file
     */
    default void save() {
    }

    /**
     * Get maximum entity in collection
     *
     * @return maximal element
     */
    V getMax();


    /**
     * Get minimum entity in collection
     *
     * @return minimal element
     */
    V getMin();

    /**
     * Get next available primary key
     *
     * @return primary key could be assigned to new element
     */
    K nextPrimaryKey();
}
