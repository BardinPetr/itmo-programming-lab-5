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

/**
 * Decorator class for providing paging for any TableModel
 */
public class PagingTableModel implements TableModel {

    private final EventListenerList listenerList = new EventListenerList();
    private final TableModel decoratee;

    @Getter
    private final PagingTableControl paginatorControl;
    private final JTable table;

    private Integer pageSize = 5;
    private Integer curPage = 0;


    /**
     * @param table
     * @param decoratee table model from which to take data
     */
    public PagingTableModel(JTable table, TableModel decoratee) {
        this.table = table;
        this.decoratee = decoratee;
        this.paginatorControl = new PagingTableControl(this::setPage, this::setPageSize);

        table.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                SwingUtilities.invokeLater(() -> onDataUpdate(null));
            }
        });

        decoratee.addTableModelListener(this::onDataUpdate);
        onDataUpdate(null);
    }

    private void onDataUpdate(TableModelEvent e) {
        var cnt = table.getHeight() / table.getRowHeight();
        if (cnt == 0)
            return;
        paginatorControl.setMaxPageSize(cnt);
        paginatorControl.setPageCount(getPageCount());
    }

    /**
     * Set current page and refresh table
     *
     * @param page page number starting from 0
     */
    private void setPage(int page) {
        if (page < 0 || page > getPageCount()) return;

        curPage = page;
        fireTableDataChanged();
    }

    private void setPageSize(int size) {
        if (size <= 0) return;

        pageSize = size;
        fireTableDataChanged();
    }

    /**
     * Convert page-relative row indet to global
     */
    public int convertIndexFromOffset(int pos) {
        return pos + curPage * pageSize;
    }

    /**
     * Get current page count
     */
    public int getPageCount() {
        return (int) Math.ceil((double) decoratee.getRowCount() / pageSize);
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
        return decoratee.isCellEditable(rowIndex, columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return decoratee.getValueAt(convertIndexFromOffset(rowIndex), columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        decoratee.setValueAt(aValue, rowIndex, columnIndex);
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

