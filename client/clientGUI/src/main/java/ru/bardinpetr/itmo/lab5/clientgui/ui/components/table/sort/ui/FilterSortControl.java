package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxList;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxListModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.panel.PopupPanel;

import javax.swing.*;
import java.awt.*;

public class FilterSortControl extends PopupPanel {

    private final CheckBoxListModel itemsModel;
    private final JButton allButton;
    private final JButton noneButton;

    public FilterSortControl(JComponent trigger, CheckBoxListModel itemsModel) {
        super(trigger);
        this.itemsModel = itemsModel;

        var buttonSize = new Dimension(70, 30);
        allButton = new JButton("All");
//        allButton.setPreferredSize(buttonSize);
        allButton.addActionListener(e -> setAll(true));
        noneButton = new JButton("None");
        noneButton.addActionListener(e -> setAll(false));
//        noneButton.setPreferredSize(buttonSize);

        var buttonBox = new Box(BoxLayout.LINE_AXIS);
        buttonBox.add(Box.createGlue());
        buttonBox.add(allButton);
        buttonBox.add(noneButton);
        buttonBox.add(Box.createGlue());


        var list = new CheckBoxList(itemsModel);
        var pane = new JScrollPane(list);
        pane.setPreferredSize(new Dimension(150, 300));
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setLayout(new BorderLayout());
        add(buttonBox, BorderLayout.NORTH);
        add(pane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void setAll(boolean status) {
        for (int i = 0; i < itemsModel.size(); i++)
            itemsModel.setChecked(i, status);
    }
}
