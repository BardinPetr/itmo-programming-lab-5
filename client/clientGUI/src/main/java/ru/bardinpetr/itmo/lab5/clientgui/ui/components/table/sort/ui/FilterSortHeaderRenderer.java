package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.button.IconButton;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxList;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxListModel;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FilterSortHeaderRenderer extends JPanel implements TableCellRenderer {
    private static final int ICON_SIZE = 20;
    private final JLabel headerLabel;
    private final IconButton directionButton;
    private SortOrder currentSort = SortOrder.UNSORTED;

    public FilterSortHeaderRenderer() {
        super(new BorderLayout());

        headerLabel = new JLabel("Test");

        directionButton = new IconButton(ICON_SIZE);
        directionButton.addActionListener(this::changeDirection);

        var filterButton = new IconButton(ICON_SIZE);
        filterButton.setIcon(getIcon(FontAwesome.FILTER));

        var buttonBox = new Box(BoxLayout.LINE_AXIS);
        buttonBox.add(directionButton);
        buttonBox.add(filterButton);

        var filterItemsModel = new CheckBoxListModel();
        for (int i = 0; i < 30; i++)
            filterItemsModel.addElement(new CheckBoxList.CheckedItem(String.valueOf(i), i % 2 == 0));
        var fsm = new FilterSortControl(filterButton, filterItemsModel);
        filterItemsModel.addCheckedEventListener(event -> {
            System.out.println(event.getElement());
        });

        setBorder(BorderFactory.createEtchedBorder());
        add(headerLabel, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.WEST);

        update();
        setVisible(true);
    }

    private void changeDirection(ActionEvent e) {
        currentSort = SortOrder.values()[(currentSort.ordinal() + 1) % SortOrder.values().length];
        update();
    }

    private Icon getIcon(IconCode code) {
        return IconFontSwing.buildIcon(code, ICON_SIZE);
    }

    private void update() {
        directionButton.setIcon(
                switch (currentSort) {
                    case UNSORTED -> getIcon(FontAwesome.SORT);
                    case ASCENDING -> getIcon(FontAwesome.SORT_ASC);
                    case DESCENDING -> getIcon(FontAwesome.SORT_DESC);
                }
        );
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        headerLabel.setText(String.valueOf(value));
        return this;
    }
}
