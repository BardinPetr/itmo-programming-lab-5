package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.actions;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ActionColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    public ActionColumn() {

    }

    public static void install(JTable table) {
        var actionColumn = new ActionColumn();
        table.setDefaultRenderer(ActionCellInfo.class, actionColumn);
        table.setDefaultEditor(ActionCellInfo.class, actionColumn);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (!(value instanceof ActionCellInfo cellControls))
            return null;

        var panel = new Box(BoxLayout.LINE_AXIS);
        for (var i : cellControls)
            panel.add(i.component());

        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        return null;
    }
}
