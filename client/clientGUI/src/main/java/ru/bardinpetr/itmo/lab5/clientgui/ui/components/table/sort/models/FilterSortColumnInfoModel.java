package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.models;

import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxListModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners.ColumnInfoEventListener;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.EventListenerList;

public class FilterSortColumnInfoModel {
    private final EventListenerList listeners = new EventListenerList();
    @Getter
    private final int columnId;
    @Getter
    private CheckBoxListModel rowItems = new CheckBoxListModel();
    @Getter
    private SortOrder sortOrder = SortOrder.UNSORTED;
    @Getter
    private String label = "NONE";

    public FilterSortColumnInfoModel(int columnId) {
        this.columnId = columnId;
    }

    public void setSortOrder(SortOrder x) {
        sortOrder = x;
        fireSortChanged();
    }

    public void setLabel(String x) {
        label = x;
        fireDataChanged();
    }

    public void setRowItems(CheckBoxListModel x) {
        rowItems = x;
        fireDataChanged();
    }

    public void fireDataChanged() {
        EventUtils.fireAll(
                listeners,
                ColumnInfoEventListener.class,
                i -> i.onColumnDataChanged(columnId)
        );
    }

    public void fireFilterChanged() {
        EventUtils.fireAll(
                listeners,
                ColumnInfoEventListener.class,
                i -> i.onFilterChanged(columnId, rowItems.getChecked())
        );
    }

    public void fireSortChanged() {
        EventUtils.fireAll(
                listeners,
                ColumnInfoEventListener.class,
                i -> i.onSortChanged(columnId, sortOrder)
        );
    }

    public void addEventListener(ColumnInfoEventListener listener) {
        listeners.add(ColumnInfoEventListener.class, listener);
    }

    public void removeEventListener(ColumnInfoEventListener listener) {
        listeners.remove(ColumnInfoEventListener.class, listener);
    }
}
