package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter;

import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.TableUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FilterRowSorter<M extends TableModel> extends RowSorter<M> {

    private final M model;
    private Map<Integer, ColumnSort> columnSortOrder;
    private RowFilter<M, Integer> rowFilter;
    private List<Integer> indexModelToView = List.of();
    private List<Integer> indexViewToModel = List.of();

    public FilterRowSorter(M model, Runnable tableUpdate) {
        this.model = model;

        columnSortOrder = new HashMap<>();

        addRowSorterListener(i -> tableUpdate.run());
        updateSort();

        model.addTableModelListener(e -> SwingUtilities.invokeLater(this::allRowsChanged));
    }

    /**
     * Run sort and filter over underlying model, build index search arrays and fire sorted event
     */
    protected void updateSort() {
        var last = indexViewToModel.stream().mapToInt(i -> i).toArray();

        indexViewToModel =
                IntStream
                        .range(0, model.getRowCount())
                        .mapToObj(i -> new Row(i, TableUtils.buildRow(model, i)))
                        .filter(i -> rowFilter == null || rowFilter.include(new RowFilterEntry(model, i.realIndex())))
                        .sorted(buildComparator())
                        .map(i -> i.realIndex)
                        .toList();

        indexModelToView = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++)
            indexModelToView.add(-1);
        for (int i = 0; i < indexViewToModel.size(); i++)
            indexModelToView.set(indexViewToModel.get(i), i);

        fireRowSorterChanged(last);
    }


    /**
     * Build comparator object from columnSortOrder
     *
     * @return Comparator or always-equals comparator if no sort columns specified
     */
    private Comparator<? super Row> buildComparator() {
        Comparator<Row> comp = null;

        // Build comparator in order of column sort changes
        var sortInTimeOrder =
                columnSortOrder
                        .entrySet().stream()
                        .sorted(
                                Comparator.comparing(i -> i.getValue().lastChange)
                        )
                        .toList();

        for (var i : sortInTimeOrder) {
            var dir = i.getValue().order();
            if (dir == SortOrder.UNSORTED) continue;

            Comparator<Row> cur = new Comparator<>() {
                @Override
                public int compare(Row r1, Row r2) {
                    var val1 = r1.data.get(i.getKey());
                    var val2 = r2.data.get(i.getKey());
                    return compareCol(val1, val2);
                }

                private <T> int compareCol(T val1, T val2) {
                    if (val1 instanceof Comparable<?> && val2 instanceof Comparable<?>)
                        return ((Comparable<T>) val1).compareTo(val2);
                    return String.valueOf(val1).compareTo(String.valueOf(val2));
                }
            };

            if (dir == SortOrder.DESCENDING)
                cur = cur.reversed();

            if (comp == null)
                comp = cur;
            else
                comp = comp.thenComparing(cur);
        }
        return comp != null ? comp : Comparator.comparing(i -> 0);
    }

    @Override
    public int convertRowIndexToModel(int index) {
        return indexViewToModel.get(index);
    }

    @Override
    public int convertRowIndexToView(int index) {
        return indexModelToView.get(index);
    }

    @Override
    public int getViewRowCount() {
        return indexViewToModel.size();
    }

    @Override
    public void allRowsChanged() {
        updateSort();
    }

    @Override
    public void modelStructureChanged() {
        columnSortOrder = new HashMap<>();
        allRowsChanged();
    }

    @Override
    public void rowsInserted(int firstRow, int endRow) {
        allRowsChanged();
    }

    @Override
    public void rowsDeleted(int firstRow, int endRow) {
        allRowsChanged();
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow) {
        allRowsChanged();
    }

    @Override
    public void rowsUpdated(int firstRow, int endRow, int column) {
        allRowsChanged();
    }

    @Override
    public List<? extends SortKey> getSortKeys() {
        return columnSortOrder
                .entrySet().stream()
                .map(i -> new SortKey(i.getKey(), i.getValue().order()))
                .toList();
    }

    @Override
    public void setSortKeys(List<? extends SortKey> keys) {
        columnSortOrder =
                keys
                        .stream()
                        .collect(Collectors.toMap(
                                SortKey::getColumn,
                                i -> new ColumnSort(i.getSortOrder())
                        ));
        updateSort();
    }

    public void setRowFilter(RowFilter<M, Integer> filter) {
        this.rowFilter = filter;
        updateSort();
    }

    @Override
    public void toggleSortOrder(int column) {
        var cur = columnSortOrder.getOrDefault(column, new ColumnSort(SortOrder.UNSORTED)).order();
        var next = SortOrder.values()[((cur.ordinal() + 1) % SortOrder.values().length)];
        columnSortOrder.put(column, new ColumnSort(next));
        updateSort();
    }

    public void setSortOrder(int column, SortOrder order) {
        columnSortOrder.put(column, new ColumnSort(order));
        updateSort();
    }

    @Override
    public M getModel() {
        return model;
    }

    @Override
    public int getModelRowCount() {
        return model.getRowCount();
    }

    protected record ColumnSort(SortOrder order, Instant lastChange) {
        public ColumnSort(SortOrder order) {
            this(order, Instant.now());
        }
    }

    protected record Row(int realIndex, Vector<?> data) {
    }

    public class RowFilterEntry extends RowFilter.Entry<M, Integer> {
        @Getter
        private final Integer identifier;
        @Getter
        private final M model;

        public RowFilterEntry(M model, Integer identifier) {
            this.model = model;
            this.identifier = identifier;
        }

        @Override
        public int getValueCount() {
            return model.getColumnCount();
        }

        @Override
        public Object getValue(int index) {
            return model.getValueAt(identifier, index);
        }
    }

}
