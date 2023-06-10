package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model;

import lombok.Getter;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableListModelAdapter<T, M extends DefaultListModel<T>> implements TableModel {
    @Getter
    private final List<String> columnNames;
    @Getter
    private final M baseModel;

    public TableListModelAdapter(M model, List<String> columnNames) {
        this.columnNames = columnNames;
        this.baseModel = model;
    }

    protected abstract Object getValueAt(T object, int column);

    protected void setValueAt(T fullObject, Object newValue, int column) {
    }

    @Override
    public int getRowCount() {
        return baseModel.getSize();
    }

    @Override
    public int getColumnCount() {
        return columnNames.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getValueAt(baseModel.get(rowIndex), columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        setValueAt(baseModel.get(rowIndex), aValue, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        baseModel.addListDataListener(TableListModelListenerAdapter.wrap(this, l));
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        baseModel.removeListDataListener(TableListModelListenerAdapter.wrap(this, l));
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    protected static class TableListModelListenerAdapter implements ListDataListener {

        private final static Map<TableModelListener, TableListModelListenerAdapter> cache = new HashMap<>();
        private final TableModelListener base;
        private final TableModel model;

        public TableListModelListenerAdapter(TableModel model, TableModelListener l) {
            this.base = l;
            this.model = model;
        }

        public static TableListModelListenerAdapter wrap(TableModel model, TableModelListener l) {
            return cache.getOrDefault(l, new TableListModelListenerAdapter(model, l));
        }

        private TableModelEvent getEvent(ListDataEvent e, int type) {
            return new TableModelEvent(model, e.getIndex0(), e.getIndex1(), TableModelEvent.ALL_COLUMNS, type);
        }

        @Override
        public void intervalAdded(ListDataEvent e) {
            base.tableChanged(getEvent(e, TableModelEvent.INSERT));
        }

        @Override
        public void intervalRemoved(ListDataEvent e) {
            base.tableChanged(getEvent(e, TableModelEvent.DELETE));
        }

        @Override
        public void contentsChanged(ListDataEvent e) {
            base.tableChanged(getEvent(e, TableModelEvent.UPDATE));
        }
    }
}
