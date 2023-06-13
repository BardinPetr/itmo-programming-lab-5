package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging;

import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Decorator class for providing paging for any TableModel
 */
public class PagingTableModel implements TableModel {

    private final EventListenerList listenerList = new EventListenerList();
    private final TableModel decoratee;

    @Getter
    private final PagingTableControl paginatorControl;
    private final JTable table;

    private Integer pageSize = 10;
    private Integer curPage = 0;


    /**
     * @param table
     * @param decoratee table model from which to take data
     */
    public PagingTableModel(JTable table, TableModel decoratee) {
        this.table = table;
        this.decoratee = decoratee;
        this.paginatorControl = new PagingTableControl(this::onPagingControlEvent);

        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(() -> onModelDataUpdate(null));
            }
        });

        decoratee.addTableModelListener(e -> SwingUtilities.invokeLater(() -> onModelDataUpdate(e)));

        Executors.newSingleThreadScheduledExecutor()
                .schedule(() -> onModelDataUpdate(null), 1, TimeUnit.SECONDS);
    }

    private void onModelDataUpdate(TableModelEvent e) {
        var cnt = table.getHeight() / table.getRowHeight();
        if (cnt == 0)
            return;

        paginatorControl.setRowCount(decoratee.getRowCount());
        paginatorControl.setMaxPageSize(cnt);
    }

    private void onPagingControlEvent(int page, int size) {
        if (page < 0 || page > getPageCount() || size <= 0) return;

        pageSize = size;
        curPage = page;
        fireTableDataChanged();
    }

    /**
     * Convert page-relative row index to global
     */
    public int convertIndexFromOffset(int pos) {
        return pos + curPage * pageSize;
    }

    /**
     * Get current page count
     */
    public int getPageCount() {
        return paginatorControl.getPageCount();
    }

    @Override
    public int getRowCount() {
        return Math.min(pageSize, decoratee.getRowCount() - curPage * pageSize);
    }

    @Override
    public int getColumnCount() {
        return decoratee.getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return decoratee.getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return decoratee.getColumnClass(columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return decoratee.isCellEditable(convertIndexFromOffset(rowIndex), columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            return decoratee.getValueAt(convertIndexFromOffset(rowIndex), columnIndex);
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        decoratee.setValueAt(aValue, convertIndexFromOffset(rowIndex), columnIndex);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        decoratee.addTableModelListener(l);
        listenerList.add(TableModelListener.class, l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        decoratee.removeTableModelListener(l);
        listenerList.remove(TableModelListener.class, l);
    }

    public void fireTableDataChanged() {
        EventUtils.fireAll(
                listenerList,
                TableModelListener.class,
                i -> i.tableChanged(new TableModelEvent(decoratee))
        );
    }
}

