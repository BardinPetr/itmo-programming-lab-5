package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging;

import lombok.Getter;
import lombok.Setter;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

/**
 * Decorator class for providing paging for any TableModel
 */
public class PagingTableModel extends AbstractTableModel {

    private final AbstractTableModel decoratee;

    @Getter
    private final PagingTableControl paginatorControl;

    @Setter
    private Integer pageSize = 5;
    private Integer curPage = 0;


    /**
     * @param decoratee table model from which to take data
     */
    public PagingTableModel(AbstractTableModel decoratee) {
        this.decoratee = decoratee;
        this.paginatorControl = new PagingTableControl(this::setPage, this::setPageSize);

        decoratee.addTableModelListener(this::fireTableChanged);
        addTableModelListener(this::onDataUpdate);
        onDataUpdate(null);
    }

    private void onDataUpdate(TableModelEvent e) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        return decoratee.getValueAt(convertIndexFromOffset(rowIndex), columnIndex);
    }
}

