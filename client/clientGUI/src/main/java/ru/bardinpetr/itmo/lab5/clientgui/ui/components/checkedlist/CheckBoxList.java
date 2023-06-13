package ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CheckBoxList extends JList<CheckBoxList.CheckedItem> {

    public CheckBoxList(CheckBoxListModel model) {
        setModel(model);
        setCellRenderer(new CheckboxListCellRenderer());
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var list = (JList<CheckedItem>) e.getSource();
                var model = (CheckBoxListModel) list.getModel();
                model.toggleChecked(list.locationToIndex(e.getPoint()));
                e.consume();
            }
        });

        model.addListDataListener(new ListDataListener() {
            @Override
            public void intervalAdded(ListDataEvent e) {
                SwingUtilities.invokeLater(() -> contentsChanged(e));
            }

            @Override
            public void intervalRemoved(ListDataEvent e) {
                contentsChanged(e);
            }

            @Override
            public void contentsChanged(ListDataEvent e) {
                repaint();
            }
        });
    }

    protected static class CheckboxListCellRenderer extends JCheckBox implements ListCellRenderer<CheckedItem> {

        @Override
        public Component getListCellRendererComponent(JList<? extends CheckedItem> list, CheckedItem value, int index, boolean isSelected, boolean cellHasFocus) {
            setSelected(value.isChecked());
            setText(value.data.toString());
            setFont(list.getFont());
            setBackground(list.getBackground());
            setForeground(list.getForeground());
            return this;
        }
    }

    @AllArgsConstructor
    @Data
    public static class CheckedItem {
        private Object data;
        private boolean checked;
    }
}
