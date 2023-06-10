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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class XTable extends JPanel {

    @Getter
    private final JTable table;
    @Getter
    private final Box bottomToolbox;
    private final FilterRowSorter<DefaultTableModel> rowSorter;
    private final RowSorterModelAdapter<DefaultTableModel> sortedModel;
    private final PagingTableModel pagedModel;
    private final DefaultTableModel model;
    private final JButton deleteButton;
    private final JButton updateButton;

    public XTable(DefaultTableModel model) {
        super(new BorderLayout());
        this.model = model;

        table = new JTable();

        var header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        rowSorter = new FilterRowSorter<>(model, table::updateUI);
        sortedModel = new RowSorterModelAdapter<>(model, rowSorter);
        pagedModel = new PagingTableModel(table, sortedModel);

        table.setModel(pagedModel);

        var externalHeader = new FilterSortTableHeader(table, model);
        externalHeader.addFilterSortParamsListener(new RowSorterEventAdapter<>(rowSorter));

        add(externalHeader, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

        deleteButton = new JButton(IconFontSwing.buildIcon(FontAwesome.TRASH_O, 16));
        deleteButton.addActionListener(this::onDelete);
        updateButton = new JButton(IconFontSwing.buildIcon(FontAwesome.PENCIL_SQUARE_O, 16));
        updateButton.addActionListener(this::onUpdate);

        bottomToolbox = new Box(BoxLayout.LINE_AXIS);
        bottomToolbox.add(updateButton);
        bottomToolbox.add(deleteButton);

        var bottom = new JPanel(new BorderLayout());
        bottom.add(bottomToolbox, BorderLayout.EAST);
        bottom.add(pagedModel.getPaginatorControl(), BorderLayout.WEST);

        add(bottom, BorderLayout.SOUTH);

        initSelection();
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
        return Arrays.stream(table.getSelectionModel().getSelectedIndices())
                .map(rowSorter::convertRowIndexToModel)
                .map(pagedModel::convertIndexFromOffset)
                .boxed()
                .toList();
    }

    private void onDelete(ActionEvent actionEvent) {
//        fireAction(new ActionEvent(this, ));
    }

    private void onUpdate(ActionEvent event) {

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
