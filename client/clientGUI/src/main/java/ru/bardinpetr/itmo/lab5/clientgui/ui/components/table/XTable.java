package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging.PagingTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.FilterRowSorter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.RowSorterEventAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.RowSorterModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui.FilterSortTableHeader;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class XTable extends JPanel {

    @Getter
    private final JTable table;
    private final FilterRowSorter<TableModel> rowSorter;
    private final PagingTableModel pagedModel;
    private final TableModel model;
    @Getter
    private Box bottomToolbox;
    protected JButton deleteButton;
    protected JButton updateButton;

    public XTable(TableModel model) {
        super(new BorderLayout());
        this.model = model;

        table = new JTable();

        var header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        rowSorter = new FilterRowSorter<>(model, table::updateUI);
        pagedModel = new PagingTableModel(
                table,
                new RowSorterModelAdapter<>(model, rowSorter)
        );

        table.setModel(pagedModel);

        var externalHeader = new FilterSortTableHeader(table, model);
        externalHeader.addFilterSortParamsListener(new RowSorterEventAdapter<>(rowSorter));

        add(externalHeader, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

        initButtonBlock();

        var bottom = new JPanel(new BorderLayout());
        bottom.add(bottomToolbox, BorderLayout.EAST);
        bottom.add(pagedModel.getPaginatorControl(), BorderLayout.WEST);

        add(bottom, BorderLayout.SOUTH);

        initSelection();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                rowSorter.allRowsChanged();
            }
        }, 500);
    }

    protected void initButtonBlock() {
        deleteButton = new JButton(IconFontSwing.buildIcon(FontAwesome.TRASH_O, 16));
        deleteButton.addActionListener(this::onDelete);
        deleteButton.setEnabled(false);
        updateButton = new JButton(IconFontSwing.buildIcon(FontAwesome.PENCIL_SQUARE_O, 16));
        updateButton.addActionListener(this::onUpdate);
        updateButton.setEnabled(false);
        var insertButton = new JButton(IconFontSwing.buildIcon(FontAwesome.PLUS_SQUARE_O, 16));
        insertButton.addActionListener(this::onInsert);

        bottomToolbox = new Box(BoxLayout.LINE_AXIS);
        bottomToolbox.add(insertButton);
        bottomToolbox.add(updateButton);
        bottomToolbox.add(deleteButton);
    }

    private void initSelection() {
        var selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            var isSingle = getSelectedIndexes().size() == 1;
            if (deleteButton != null)
                deleteButton.setEnabled(isSingle);
            if (updateButton != null)
                updateButton.setEnabled(isSingle);
        });
    }

    public List<Integer> getSelectedIndexes() {
        return Arrays
                .stream(table.getSelectionModel().getSelectedIndices())
                .map(rowSorter::convertRowIndexToModel)
                .map(pagedModel::convertIndexFromOffset)
                .boxed()
                .toList();
    }

    protected void onDelete(ActionEvent actionEvent) {
    }

    protected void onUpdate(ActionEvent event) {
    }

    protected void onInsert(ActionEvent event) {
    }

    protected void fireAction(ActionEvent event) {
        EventUtils.fireAll(
                listenerList,
                ActionListener.class,
                i -> i.actionPerformed(event)
        );
    }

    public void addActionListener(ActionListener listener) {
        listenerList.add(ActionListener.class, listener);
    }

    public void removeActionListener(ActionListener listener) {
        listenerList.remove(ActionListener.class, listener);
    }
}
