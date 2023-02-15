package ru.bardinpetr.itmo.lab5.models.data.collection;

import java.util.Set;

public interface ISetCollection<K, V extends IIdentifiableEntry<K>> extends Set<V> {
}
