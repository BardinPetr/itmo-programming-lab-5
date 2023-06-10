package ru.bardinpetr.itmo.lab5.clientgui.ui.utils;

import javax.swing.table.TableModel;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TableUtils {

    public static Vector<Object> buildRow(TableModel model, int row) {
        return IntStream
                .range(0, model.getColumnCount())
                .mapToObj(i -> model.getValueAt(row, i))
                .collect(Collectors.toCollection(Vector::new));
    }
}
