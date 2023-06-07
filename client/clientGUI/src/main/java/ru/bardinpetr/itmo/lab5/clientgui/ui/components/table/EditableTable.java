package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TypedTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging.PagingTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.FilterRowSorter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.RowSorterModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui.FilterSortHeaderRenderer;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.GetSelfInfoCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.ShowCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;
import ru.bardinpetr.itmo.lab5.models.commands.requests.PagingAPICommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class EditableTable extends JPanel {


    private final FilterRowSorter<WorkerTableModel> sorter;

    public EditableTable() {
        List<Worker> data = null;
        Integer ownerId = null;
        try {
            data = ((ShowCommand.ShowCommandResponse) APIProvider.getConnector().call(new ShowCommand(0, PagingAPICommand.FULL_COUNT))).getResult();
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
        }

        var model = new WorkerTableModel(ownerId);
        model.setData(data);

        var table = new JTable();

        table.setColumnModel(new WorkerColumnModel());

        var header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        sorter = new FilterRowSorter<>(model);
        var rsma = new RowSorterModelAdapter<>(model, sorter);
        var pmodel = new PagingTableModel(rsma);
        table.setModel(pmodel);

        var deleteButton = new JButton("Delete");
        var deleteGreaterButton = new JButton("DG");
        deleteButton.addActionListener(l -> sorter.toggleSortOrder(0));
        deleteGreaterButton.addActionListener(l -> sorter.toggleSortOrder(1));
//        var selectionModel = table.getSelectionModel();
//        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//        selectionModel.addListSelectionListener(e -> {
//            if (e.getValueIsAdjusting()) return;
//
//            var indexes =
//                    Arrays.stream(table.getSelectionModel().getSelectedIndices())
//                            .map(pmodel::convertIndexFromOffset)
//                            .boxed().toList();
//            deleteButton.setEnabled(indexes.size() > 0);
//            deleteGreaterButton.setEnabled(indexes.size() == 1);
//        });


        setLayout(new BorderLayout());
        add(table.getTableHeader(), BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

        var buttons = new Box(BoxLayout.LINE_AXIS);
        buttons.add(deleteButton);
        buttons.add(deleteGreaterButton);

        var filterMenu = new FilterSortHeaderRenderer();
        buttons.add(filterMenu);

        var bottom = new JPanel(new BorderLayout());
        bottom.add(buttons, BorderLayout.EAST);
        bottom.add(pmodel.getPaginatorControl(), BorderLayout.WEST);

        add(bottom, BorderLayout.SOUTH);
    }

    public static void main(String[] args) throws APIClientException {
        UIResources.getInstance();
        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(new StoredJWTCredentials((JWTLoginResponse) ((AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()));

        var f = new JFrame();
        f.getContentPane().add(new EditableTable());
        f.setSize(600, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    static class WorkerTableModel extends TypedTableModel<Worker> {
        private final Integer ownerId;

        public WorkerTableModel(Integer ownerId) {
            super(new String[]{"ID", "Owner", "Name"});
            this.ownerId = ownerId;

//            Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(
//                    () -> {
//                        data.add(new Worker(1234, "ad", ZonedDateTime.now(), null, "asdsad", 34.3f, Date.from(Instant.now()), LocalDate.now(), new Coordinates(2, 4), null, null));
//                        this.fireTableDataChanged();
//                    },
//                    0,
//                    1,
//                    TimeUnit.SECONDS
//            );
        }

        @Override
        protected List<Object> convertRow(Worker object) {
            return List.of(
                    object.getId(),
                    object.getOwnerUsername(),
                    object.getName()
            );
        }


//        @Override
//        public int getColumnCount() {
//            return 3;
//        }

//        @Override
//        public Object getValueAt(int rowIndex, int columnIndex) {
//            var worker = get(rowIndex);
//            return switch (columnIndex) {
//                case 0 -> worker.getId();
//                case 1 -> worker.getOwnerUsername();
//                case 2 -> worker.getName();
//                case 3 -> worker.getCreationDate();
//                case 4 -> worker.getCoordinates().getX();
//                case 5 -> worker.getCoordinates().getY();
//                default -> throw new IllegalStateException("Unexpected value");
//            };
//        }

//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            return getValueAt(0, columnIndex).getClass();
//        }

//        @Override
//        public boolean isCellEditable(int rowIndex, int columnIndex) {
//            return true;
////            return data.get(rowIndex).getOwner().equals(ownerId);
//        }
    }

    static class WorkerColumnModel extends DefaultTableColumnModel {
        private static final List<String> columnNames = List.of("ID", "Owner", "Name");

        public WorkerColumnModel() {
            for (int i = 0; i < columnNames.size(); i++) {
                var col = new TableColumn(i, 50);

                var filterMenu = new FilterSortHeaderRenderer();
                col.setHeaderRenderer(filterMenu);
                addColumn(col);
            }
        }
    }
}
