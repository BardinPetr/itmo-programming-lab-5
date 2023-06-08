package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter;

import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.event.RowSorterEvent;
import javax.swing.event.RowSorterListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.Map;

public class RowSorterModelAdapter<M extends DefaultTableModel> implements TableModel {

    private final RowSorter<M> sorter;
    private final M model;

    public RowSorterModelAdapter(M model, RowSorter<M> sorter) {
        this.sorter = sorter;
        this.model = model;
    }

    /**
     * Uses sorter's convertRowIndexToModel for recalculating rowIndex
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        var row = sorter.convertRowIndexToModel(rowIndex);
        return model.getValueAt(row, columnIndex);
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
    public String getColumnName(int columnIndex) {
        return model.getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return model.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return model.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        model.setValueAt(aValue, rowIndex, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        model.addTableModelListener(l);
//        sorter.addRowSorterListener(SorterListenerAdapter.of(l));
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        model.removeTableModelListener(l);
//        sorter.removeRowSorterListener(SorterListenerAdapter.of(l));
    }

    @RequiredArgsConstructor
    protected static class SorterListenerAdapter implements RowSorterListener {
        private static final Map<TableModelListener, SorterListenerAdapter> cache = new HashMap<>();
        private final TableModelListener listener;

        public static SorterListenerAdapter of(TableModelListener listener) {
            if (!cache.containsKey(listener))
                cache.put(listener, new SorterListenerAdapter(listener));
            return cache.get(listener);
        }

        @Override
        public void sorterChanged(RowSorterEvent e) {
            RowSorter<?> src = e.getSource();
            TableModel model = (TableModel) src.getModel();
            listener.tableChanged(new TableModelEvent(
                    model, 0, e.getPreviousRowCount()
            ));
        }
    }
}