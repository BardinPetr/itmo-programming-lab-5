package ru.bardinpetr.itmo.lab5.clientgui.models;

import ru.bardinpetr.itmo.lab5.clientgui.utils.ListUtil;

import javax.swing.*;
import java.util.List;
import java.util.stream.Stream;

public class ExtendedListModel<T> extends DefaultListModel<T> {


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
