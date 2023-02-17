package ru.bardinpetr.itmo.lab5.server.dao;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * DAO for operations with filters.
 *
 * @param <K> Type of primary key
 * @param <V> Entity type
 */
public interface ICollectionFilteredDAO<K, V extends IKeyedEntity<K>> extends ICollectionBaseDAO<K, V> {

    /**
     * Return all items from collection matching predicate
     *
     * @param condition if predicate is true item is included
     * @return list of filtered items
     */
    default List<V> filter(Predicate<V> condition) {
        return readAll().stream().filter(condition).toList();
    }

    /**
     * Remove from collection all elements matching predicate
     *
     * @param condition if predicate return true, object will be deleted
     */
    default void remove(Predicate<V> condition) {
        filter(condition).forEach(obj -> remove(obj.getPrimaryKey()));
    }

    /**
     * Returns all elements with function applied to each
     *
     * @param extractor function to map from collection item to output object
     * @return list of extracted fields for all items
     */
    default <T> List<T> getMapped(Function<V, T> extractor) {
        return readAll().stream().map(extractor).toList();
    }
}
