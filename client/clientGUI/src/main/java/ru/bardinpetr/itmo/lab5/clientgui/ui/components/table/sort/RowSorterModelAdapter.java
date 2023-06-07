package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class RowSorterModelAdapter<M extends DefaultTableModel> extends AbstractTableModel {

    private final RowSorter<M> sorter;
    private final M model;

    public RowSorterModelAdapter(M model, RowSorter<M> sorter) {
        this.model = model;
        this.sorter = sorter;
        sorter.addRowSorterListener(e -> fireTableDataChanged());
        model.addTableModelListener(e -> fireTableDataChanged());
    }

    @Override
    public int getRowCount() {
        return sorter.getViewRowCount();
    }

    @Override
    public int getColumnCount() {
        return model.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var row = sorter.convertRowIndexToModel(rowIndex);
        return model.getValueAt(row, columnIndex);
    }
}