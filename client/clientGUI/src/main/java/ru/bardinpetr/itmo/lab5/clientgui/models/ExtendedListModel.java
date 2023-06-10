package ru.bardinpetr.itmo.lab5.clientgui.models;

import ru.bardinpetr.itmo.lab5.clientgui.utils.ListUtil;
import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;

import javax.swing.*;
import java.util.List;
import java.util.stream.Stream;

public class ExtendedListModel<T extends IKeyedEntity<Integer>> extends DefaultListModel<T> {

    public T getByPK(int id) {
        return asStream()
                .filter(i -> i.getPrimaryKey().equals(id))
                .findAny()
                .orElse(null);
    }

    public List<T> asList() {
        return ListUtil
                .stream(elements())
                .toList();
    }

    public Stream<T> asStream() {
        return ListUtil
                .stream(elements());
    }
}
