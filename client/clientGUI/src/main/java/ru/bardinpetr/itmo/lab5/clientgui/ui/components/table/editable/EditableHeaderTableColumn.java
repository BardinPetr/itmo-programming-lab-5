package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.editable;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

public class EditableHeaderTableColumn extends TableColumn {

    @Getter
    @Setter
    protected TableCellEditor headerEditor;

    public EditableHeaderTableColumn() {
        setHeaderEditor(new DefaultCellEditor(new JTextField()));
    }
}
