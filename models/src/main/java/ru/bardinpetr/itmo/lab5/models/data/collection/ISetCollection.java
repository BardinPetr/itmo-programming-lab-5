package ru.bardinpetr.itmo.lab5.models.data.collection;

import java.util.Set;

/**
 * Collection with elements having primary key based on Set
 *
 * @param <K> Primary key type
 * @param <V> Element type
 */
public interface ISetCollection<K, V extends IIdentifiableEntry<K>> extends Set<V> {
}
