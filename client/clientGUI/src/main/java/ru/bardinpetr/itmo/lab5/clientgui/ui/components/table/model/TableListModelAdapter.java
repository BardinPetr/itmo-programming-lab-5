package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model;

import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class TableListModelAdapter<T, M extends DefaultListModel<T>> implements TableModel {
    private final EventListenerList listenerList = new EventListenerList();

    @Getter
    private final M baseModel;
    @Getter
    private List<String> columnNames;

    public TableListModelAdapter(M model, int columnCount) {
        baseModel = model;
        columnNames = new ArrayList<>();
        for (int i = 0; i < columnCount; i++)
            columnNames.add("...");
        updateColumnNames();
    }

    protected abstract Object getValueAt(T object, int column);

    protected abstract List<String> getColumnNames();

    protected void setValueAt(T fullObject, Object newValue, int column) {
    }

    protected void updateColumnNames() {
        SwingUtilities.invokeLater(() -> {
            var newColumns = getColumnNames();
            if (newColumns.size() != columnNames.size())
                return;

            columnNames = newColumns;
            EventUtils.fireAll(
                    listenerList,
                    TableModelListener.class,
                    i -> i.tableChanged(new TableModelEvent(this))
            );
        });
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
        try {
            return getValueAt(baseModel.get(rowIndex), columnIndex);
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        setValueAt(baseModel.get(rowIndex), aValue, columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        baseModel.addListDataListener(TableListModelListenerAdapter.wrap(this, l));
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        baseModel.removeListDataListener(TableListModelListenerAdapter.wrap(this, l));
        listenerList.remove(TableModelListener.class, l);
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
            if (!cache.containsKey(l))
                cache.put(l, new TableListModelListenerAdapter(model, l));
            return cache.get(l);
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
