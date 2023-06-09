package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.editable;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditableHeaderRenderer implements TableCellRenderer {
    private final JComponent editor;
    private JTable table = null;
    private MouseEventReposter reporter = null;

    public EditableHeaderRenderer(JComponent editor) {
        this.editor = editor;
        editor.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        if (table != null && this.table != table) {
            this.table = table;
            final JTableHeader header = table.getTableHeader();
            if (header != null) {
                editor.setForeground(header.getForeground());
                editor.setBackground(header.getBackground());
                editor.setFont(header.getFont());
                reporter = new MouseEventReposter(header, col, editor);
                header.addMouseListener(reporter);
            }
        }

        if (reporter != null) reporter.setColumn(col);

        return editor;
    }

    static public class MouseEventReposter extends MouseAdapter {

        private final JTableHeader header;
        private final Component editor;
        private Component dispatchComponent;
        private int column;

        public MouseEventReposter(JTableHeader header, int column, Component editor) {
            this.header = header;
            this.column = column;
            this.editor = editor;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        private void setDispatchComponent(MouseEvent e) {
            int col = header.getTable().columnAtPoint(e.getPoint());
            if (col != column || col == -1) return;

            Point p = e.getPoint();
            Point p2 = SwingUtilities.convertPoint(header, p, editor);
            dispatchComponent = SwingUtilities.getDeepestComponentAt(editor, p2.x, p2.y);
        }

        private void repostEvent(MouseEvent e) {
            if (dispatchComponent == null) return;

            MouseEvent e2 = SwingUtilities.convertMouseEvent(header, e, dispatchComponent);
            dispatchComponent.dispatchEvent(e2);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (header.getResizingColumn() == null) {
                Point p = e.getPoint();

                int col = header.getTable().columnAtPoint(p);
                if (col != column || col == -1) return;

                int index = header.getColumnModel().getColumnIndexAtX(p.x);
                if (index == -1) return;

                editor.setBounds(header.getHeaderRect(index));
                header.add(editor);
                editor.validate();
                setDispatchComponent(e);
                repostEvent(e);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            repostEvent(e);
            dispatchComponent = null;
            header.remove(editor);
        }
    }
}
