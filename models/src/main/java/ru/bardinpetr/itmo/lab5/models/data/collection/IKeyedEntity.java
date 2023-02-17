package ru.bardinpetr.itmo.lab5.models.data.collection;

/**
 * Wrapper for object with primary key
 *
 * @param <K> Primary key type
 */
public interface IKeyedEntity<K> {
    /**
     * Returns primary key for object implementing this interface
     *
     * @return primary key
     */
    K getPrimaryKey();

    /**
     * Sets primary key for object
     */
    void setPrimaryKey(K key);
}
