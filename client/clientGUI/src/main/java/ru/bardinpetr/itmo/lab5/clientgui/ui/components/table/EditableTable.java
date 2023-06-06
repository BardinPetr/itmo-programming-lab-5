package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging.PagingTableModel;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class EditableTable extends JPanel {


    public EditableTable() {
        List<Worker> data = null;
        Integer ownerId = null;
        try {
            data = ((ShowCommand.ShowCommandResponse) APIProvider.getConnector().call(new ShowCommand(0, PagingAPICommand.FULL_COUNT))).getResult();
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
        }


//        table.setFillsViewportHeight(true);
//        var sorter = new TableRowSorter<>(model);
//        table.setRowSorter(sorter);
//        table.setRowHeight(40);
//        table.setDefaultRenderer(ActionCellRenderer.ActionCell.class, new ActionCellRenderer());
//        table.set(ActionCellRenderer.ActionCell.class, new ActionCellRenderer());
//        table.getTableHeader().setDefaultRenderer(new FilterHeaderRenderer());
//        new ButtonColumn(table, new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println(e);
//            }
//        }, 3);

        var model = new WorkerTableModel(ownerId, data);
        var pmodel = new PagingTableModel(model);
        var table = new JTable(pmodel, new WorkerColumnModel());

        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);
        add(pmodel.getPaginatorControl(), BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws APIClientException {
        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(new StoredJWTCredentials((JWTLoginResponse) ((AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()));

        var f = new JFrame();
        f.getContentPane().add(new EditableTable());
        f.setSize(600, 400);
        f.setVisible(true);
    }

//    static class ActionCellRenderer extends JButton implements TableCellRenderer, TableCellEditor {
//
//        public ActionCellRenderer() {
//            super("B");
//        }
//
//        @Override
//        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
////            var data = (ActionCell) value;
////            var panel = new Box(BoxLayout.LINE_AXIS);
////            panel.add(new JButton("A"));
////            var b = new JButton("B");
//////            panel.add(b);
//////            panel.setOpaque(true);
////            b.setOpaque(true);
////            b.addActionListener(System.out::println);
////            return b;
//            return this;
//        }
//
//        @Override
//        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//            return this;
//        }
//
//        @Override
//        public Object getCellEditorValue() {
//            return "";
//        }
//
//        @Override
//        public boolean isCellEditable(EventObject anEvent) {
//            return true;
//        }
//
//        @Override
//        public boolean shouldSelectCell(EventObject anEvent) {
//            return true;
//        }
//
//        @Override
//        public boolean stopCellEditing() {
//            return true;
//        }
//
//        @Override
//        public void cancelCellEditing() {
//
//        }
//
//        @Override
//        public void addCellEditorListener(CellEditorListener l) {
//
//        }
//
//        @Override
//        public void removeCellEditorListener(CellEditorListener l) {
//
//        }
//
//        record ActionCell() {
//
//        }
//    }


    static class WorkerColumnModel extends DefaultTableColumnModel {
        private static final List<String> columnNames = List.of("ID", "Owner", "Name", "Action");

        public WorkerColumnModel() {
            for (int i = 0; i < columnNames.size(); i++) {
                var col = new TableColumn(i, 50);
                col.setHeaderValue(columnNames.get(i));
                addColumn(col);
            }
//            tableColumns.lastElement().setCellRenderer(new ActionCellRenderer());
//            tableColumns.lastElement().setCellEditor(new ActionCellRenderer());
        }
    }


    static class WorkerTableModel extends AbstractTableModel {
        private final List<Worker> data;
        private final Integer ownerId;

        public WorkerTableModel(Integer ownerId, List<Worker> data) {
            this.ownerId = ownerId;
            this.data = data;

            Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
                    () -> {
                        data.add(new Worker(1234, "ad", ZonedDateTime.now(), null, "asdsad", 34.3f, Date.from(Instant.now()), LocalDate.now(), new Coordinates(2, 4), null, null));
                        this.fireTableDataChanged();
                    },
                    0,
                    1,
                    TimeUnit.SECONDS
            );
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var worker = data.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> worker.getId();
                case 1 -> worker.getOwnerUsername();
                case 2 -> worker.getName();
                case 3 -> worker.getName();
                default -> throw new IllegalStateException("Unexpected value");
            };
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
//            return data.get(rowIndex).getOwner().equals(ownerId);
        }
    }
}
