package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.button.IconButton;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxList;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist.CheckBoxListModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.panel.PopupPanel;

import javax.swing.*;
import java.awt.*;

public class SelectFilterControl extends PopupPanel {

    private final CheckBoxListModel itemsModel;

    public SelectFilterControl(JComponent trigger, CheckBoxListModel itemsModel) {
        super(trigger);
        this.itemsModel = itemsModel;

        var allButton = new IconButton(IconFontSwing.buildIcon(FontAwesome.CHECK_SQUARE_O, 24));
        allButton.addActionListener(e -> setAll(true));
        var noneButton = new IconButton(IconFontSwing.buildIcon(FontAwesome.TRASH_O, 24));
        noneButton.addActionListener(e -> setAll(false));

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

        setBorder(BorderFactory.createRaisedBevelBorder());
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
