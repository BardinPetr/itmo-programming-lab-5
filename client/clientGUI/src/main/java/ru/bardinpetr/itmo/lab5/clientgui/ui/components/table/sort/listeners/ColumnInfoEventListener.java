package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners;

import java.util.EventListener;

public interface ColumnInfoEventListener extends EventListener, FilterSortParamsEventListener {

    void onColumnDataChanged(int column);
}