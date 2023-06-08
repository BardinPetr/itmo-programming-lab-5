package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners.FilterSortParamsEventListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class RowSorterEventAdapter<M extends DefaultTableModel> implements FilterSortParamsEventListener {

    private final Map<Integer, RowFilter<M, Integer>> filters = new HashMap<>();
    private final FilterRowSorter<M> sorter;

    public RowSorterEventAdapter(FilterRowSorter<M> sortedModel) {
        this.sorter = sortedModel;
    }

    private RowFilter<M, Integer> getCombinedFilter() {
        return new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends M, ? extends Integer> entry) {
                return filters
                        .values()
                        .stream()
                        .allMatch(i -> i.include(entry));
            }
        };
    }

    private RowFilter<M, Integer> buildFilterFromEqualsList(int column, Set<Object> checked) {
        return new RowFilter<>() {
            @Override
            public boolean include(Entry<? extends M, ? extends Integer> entry) {
                return checked.contains(entry.getValue(column));
            }
        };
    }

    @Override
    public void onFilterChanged(int column, Set<Object> checked) {
        filters.put(column, buildFilterFromEqualsList(column, checked));
        sorter.setRowFilter(getCombinedFilter());
    }

    @Override
    public void onSortChanged(int column, SortOrder order) {
        sorter.setSortOrder(column, order);
    }
}
