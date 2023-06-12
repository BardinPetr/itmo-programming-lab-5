package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxList;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners.ColumnInfoEventListener;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners.FilterSortParamsEventListener;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.models.FilterSortColumnInfoModel;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterSortTableHeader extends JPanel implements ColumnInfoEventListener {

    private static final long FILTER_AGGREGATE_DURATION_MILLIS = 100;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private final List<FilterSortColumnInfoModel> colInfoModels;
    private final TableModel sourceModel;
    private final JTable table;
    private ScheduledFuture<?> filterEventDelayedFire = null;

    /**
     * @param table       JTable
     * @param sourceModel the source model (not filtered)
     */
    public FilterSortTableHeader(JTable table, TableModel sourceModel) {
        this.sourceModel = sourceModel;
        this.table = table;


        colInfoModels =
                IntStream
                        .range(0, sourceModel.getColumnCount())
                        .mapToObj(FilterSortColumnInfoModel::new)
                        .peek(i -> i.addEventListener(this))
                        .toList();

        // Create columns from table's preferred size
        var columnModel = table.getColumnModel();

        setLayout(new GridLayout(1, columnModel.getColumnCount(), 2, 0));

        colInfoModels.stream()
                .map(FilterSortHeaderCell::new)
                .peek(i -> i.setPreferredSize(
                        new Dimension(
                                columnModel
                                        .getColumn(i.getInfoModel().getColumnId())
                                        .getPreferredWidth(),
                                (int) (table.getRowHeight() * 1.5)
                        )
                ))
                .forEach(this::add);

        model().addTableModelListener(this::modelChanged);
        modelChanged(null);
    }

    private void modelChanged(TableModelEvent e) {
        for (var m : colInfoModels) {
            m.setLabel(model().getColumnName(m.getColumnId()));

//            m.getRowItems().updateWith(getColumnValues(m.getColumnId()));
            m.getRowItems().clear();
            m.getRowItems().addAll(
                    getColumnValues(m.getColumnId())
                            .stream()
                            .map(i -> new CheckBoxList.CheckedItem(i, true))
                            .toList()
            );
        }
    }

    private Set<Object> getColumnValues(int colId) {
        return IntStream
                .range(0, sourceModel.getRowCount())
                .mapToObj(i -> sourceModel.getValueAt(i, colId))
                .collect(Collectors.toCollection(TreeSet::new));
    }

    private TableModel model() {
        return table.getModel();
    }

    @Override
    public void onColumnDataChanged(int column) {
    }

    @Override
    public void onFilterChanged(int columnId, Set<Object> checked) {
        if (filterEventDelayedFire != null) {
            if (!filterEventDelayedFire.isDone())
                filterEventDelayedFire.cancel(true);
            filterEventDelayedFire = null;
        }

        filterEventDelayedFire = executor.schedule(
                () -> EventUtils.fireAll(
                        listenerList,
                        FilterSortParamsEventListener.class,
                        i -> i.onFilterChanged(columnId, checked)
                ),
                FILTER_AGGREGATE_DURATION_MILLIS,
                TimeUnit.MILLISECONDS
        );
    }

    @Override
    public void onSortChanged(int columnId, SortOrder order) {
        EventUtils.fireAll(
                listenerList,
                FilterSortParamsEventListener.class,
                i -> i.onSortChanged(columnId, order)
        );
    }


    public void addFilterSortParamsListener(FilterSortParamsEventListener x) {
        listenerList.add(FilterSortParamsEventListener.class, x);
    }

    public void removeFilterSortParamsListener(FilterSortParamsEventListener x) {
        listenerList.remove(FilterSortParamsEventListener.class, x);
    }
}
