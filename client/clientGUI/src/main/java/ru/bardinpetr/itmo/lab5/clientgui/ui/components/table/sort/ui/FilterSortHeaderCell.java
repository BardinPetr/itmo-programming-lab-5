package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui;

import jiconfont.IconCode;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import lombok.Getter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.button.IconButton;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.listeners.ColumnInfoEventListener;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.models.FilterSortColumnInfoModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Set;

public class FilterSortHeaderCell extends JPanel {
    private static final int ICON_SIZE = 20;

    @Getter
    private final FilterSortColumnInfoModel infoModel;

    private final IconButton directionButton;
    private final JLabel headerLabel;


    public FilterSortHeaderCell(FilterSortColumnInfoModel infoModel) {
        super(new BorderLayout());
        this.infoModel = infoModel;

        headerLabel = new JLabel();

        directionButton = new IconButton(ICON_SIZE);
        directionButton.addActionListener(this::changeDirection);

        var filterButton = new IconButton(ICON_SIZE);
        filterButton.setIcon(getIcon(FontAwesome.FILTER));

        var buttonBox = new Box(BoxLayout.LINE_AXIS);
        buttonBox.add(directionButton);
        buttonBox.add(filterButton);

        var rowItems = infoModel.getRowItems();
        rowItems.addCheckedEventListener(i -> infoModel.fireFilterChanged());
        var fsm = new SelectFilterControl(filterButton, rowItems);

        setBorder(BorderFactory.createEtchedBorder());
        add(headerLabel, BorderLayout.CENTER);
        add(buttonBox, BorderLayout.WEST);

        infoModel.addEventListener(new ColumnInfoEventListener() {
            @Override
            public void onColumnDataChanged(int column) {
                update();
            }

            @Override
            public void onFilterChanged(int columnId, Set<Object> checked) {

            }

            @Override
            public void onSortChanged(int columnId, SortOrder order) {
                update();
            }
        });
        update();

        setVisible(true);
    }

    private void changeDirection(ActionEvent e) {
        infoModel.setSortOrder(
                SortOrder.values()[
                        (infoModel.getSortOrder().ordinal() + 1) % SortOrder.values().length
                        ]
        );
    }

    private void update() {
        headerLabel.setText(infoModel.getLabel());
        directionButton.setIcon(
                switch (infoModel.getSortOrder()) {
                    case UNSORTED -> getIcon(FontAwesome.SORT);
                    case ASCENDING -> getIcon(FontAwesome.SORT_ASC);
                    case DESCENDING -> getIcon(FontAwesome.SORT_DESC);
                }
        );
    }

    private Icon getIcon(IconCode code) {
        return IconFontSwing.buildIcon(code, ICON_SIZE);
    }
}
