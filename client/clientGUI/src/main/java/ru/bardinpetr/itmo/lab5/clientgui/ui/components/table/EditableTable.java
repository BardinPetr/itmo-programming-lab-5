package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TypedTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.paging.PagingTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.FilterRowSorter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.RowSorterEventAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.sorter.RowSorterModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.sort.ui.FilterSortTableHeader;
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
import java.awt.*;
import java.time.ZonedDateTime;
import java.util.List;

public class EditableTable extends JPanel {


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

        var header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);

        var rowSorter = new FilterRowSorter<>(model, table::updateUI);
        var sortedModel = new RowSorterModelAdapter<>(model, rowSorter);
        var pagedModel = new PagingTableModel(table, sortedModel);

        table.setModel(pagedModel);

        var externalHeader = new FilterSortTableHeader(table, model);
        externalHeader.addFilterSortParamsListener(new RowSorterEventAdapter<>(rowSorter));

        var deleteButton = new JButton("Delete");
        var deleteGreaterButton = new JButton("DG");

        //        var selectionModel = table.getSelectionModel();
        //        selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        //        selectionModel.addListSelectionListener(e -> {
        //            if (e.getValueIsAdjusting()) return;
        //
        //            var indexes =
        //                    Arrays.stream(table.getSelectionModel().getSelectedIndices())
        //                            .map(pagedModel::convertIndexFromOffset)
        //                            .boxed().toList();
        //            deleteButton.setEnabled(indexes.size() > 0);
        //            deleteGreaterButton.setEnabled(indexes.size() == 1);
        //        });

        setLayout(new BorderLayout());
        add(externalHeader, BorderLayout.NORTH);
        add(table, BorderLayout.CENTER);

        var buttons = new Box(BoxLayout.LINE_AXIS);
        buttons.add(deleteButton);
        buttons.add(deleteGreaterButton);

        var bottom = new JPanel(new BorderLayout());
        bottom.add(buttons, BorderLayout.EAST);
        bottom.add(pagedModel.getPaginatorControl(), BorderLayout.WEST);

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
            this.ownerId = ownerId;
            setColumnIdentifiers(new String[]{String.valueOf(ZonedDateTime.now()), "Owner", "Name"});
        }

        @Override
        protected List<Object> convertRow(Worker object) {
            return List.of(
                    object.getId(),
                    object.getOwnerUsername(),
                    object.getName()
            );
        }
    }
}
