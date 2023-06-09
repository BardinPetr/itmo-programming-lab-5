package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model;

import lombok.Getter;
import lombok.NonNull;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public abstract class TypedTableModel<T extends Comparable<T>> extends DefaultTableModel {

    @Getter
    private List<T> originalData;

    public final void setData(@NonNull List<T> newData) {
        originalData = newData;
        setDataVector(
                new Vector<>(
                        newData.stream()
                                .map(i -> new Vector<>(this.convertRow(i)))
                                .toList()
                ),
                columnIdentifiers
        );
    }

    protected abstract List<Object> convertRow(T object);
}
