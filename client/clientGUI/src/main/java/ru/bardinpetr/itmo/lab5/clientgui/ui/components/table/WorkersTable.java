package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TypedTableModel;
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
import java.util.List;
import java.util.Set;

public class WorkersTable extends XTable {

    public WorkersTable(WorkerTableModel model) {
        super(model);
    }

    public static void main(String[] args) throws APIClientException {
        UIResources.getInstance();
        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(new StoredJWTCredentials((JWTLoginResponse) ((AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()));

        List<Worker> data = null;
        Integer ownerId = null;
        try {
            data = ((ShowCommand.ShowCommandResponse) APIProvider.getConnector().call(new ShowCommand(0, PagingAPICommand.FULL_COUNT))).getResult();
            ownerId = ((GetSelfInfoCommand.GetSelfInfoResponse) APIProvider.getConnector().call(new GetSelfInfoCommand())).getId();
        } catch (APIClientException ignored) {
        }
        var model = new WorkerTableModel(ownerId);
        model.setData(data);

        var table = new WorkersTable(model);


        var f = new JFrame();
        f.getContentPane().add(table);
        f.setSize(600, 400);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    public static class WorkerTableModel extends TypedTableModel<Worker> {
        private final List<String> columnNames = List.of("ID", "Owner", "Name");
        private final Set<String> lockedColumns = Set.of("ID", "Owner");
        private final Integer ownerId;

        public WorkerTableModel(Integer ownerId) {
            setColumnIdentifiers(columnNames.toArray());
            this.ownerId = ownerId;
        }

        @Override
        protected List<Object> convertRow(Worker object) {
            return List.of(
                    object.getId(),
                    object.getOwnerUsername(),
                    object.getName()
            );
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            var isUnlocked = !lockedColumns.contains(columnNames.get(column));
            var isOwned = getOriginalData().get(row).getOwner().equals(ownerId);
            return isUnlocked && isOwned;
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            System.out.printf("%dx%d: %s\n", row, column, aValue);
        }
    }
}
