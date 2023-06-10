package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class RowSorterModelAdapter<M extends TableModel> implements TableModel {

    private final RowSorter<M> sorter;
    private final M model;

    public RowSorterModelAdapter(M model, RowSorter<M> sorter) {
        this.sorter = sorter;
        this.model = model;
    }

    /**
     * Uses sorter's convertRowIndexToModel for recalculating rowIndex
     */
    protected int calculateRow(int viewRow) {
        return sorter.convertRowIndexToModel(viewRow);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return model.getValueAt(calculateRow(rowIndex), columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return model.isCellEditable(calculateRow(rowIndex), columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        model.setValueAt(aValue, calculateRow(rowIndex), columnIndex);
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
    public void addTableModelListener(TableModelListener l) {
        model.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        model.removeTableModelListener(l);
    }
}