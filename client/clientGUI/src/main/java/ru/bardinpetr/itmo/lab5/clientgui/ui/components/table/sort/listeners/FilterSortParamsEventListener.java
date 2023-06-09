package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners;

import javax.swing.*;
import java.util.EventListener;
import java.util.Set;

public interface FilterSortParamsEventListener extends EventListener {
    void onFilterChanged(int column, Set<Object> checked);

    void onSortChanged(int column, SortOrder order);
}