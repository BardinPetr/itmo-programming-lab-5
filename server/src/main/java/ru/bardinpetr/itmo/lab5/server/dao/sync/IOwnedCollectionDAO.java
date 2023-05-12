package ru.bardinpetr.itmo.lab5.server.dao.sync;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;
import ru.bardinpetr.itmo.lab5.models.data.collection.IOwnedEntity;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionDAO;

/**
 * @param <K> Type of primary key
 * @param <V> Entity type
 */
public interface IOwnedCollectionDAO<K, V extends IKeyedEntity<K> & IOwnedEntity> extends ICollectionDAO<K, V> {
    /**
     * Inserts object and sets its owner
     *
     * @return primary key assigned
     */
    K add(String user, V obj);

    /**
     * Updates object only if it was created by the same user
     *
     * @throws NotOwnedException if user doesn't own object
     */
    void update(String user, K id, V obj);

    /**
     * Removes object only if it was created by the same user
     *
     * @throws NotOwnedException if user doesn't own object
     */
    boolean remove(String user, K id);
}
