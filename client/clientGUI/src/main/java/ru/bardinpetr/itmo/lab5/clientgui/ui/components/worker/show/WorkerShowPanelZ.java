package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show;

import ru.bardinpetr.itmo.lab5.client.api.connectors.APIProvider;
import ru.bardinpetr.itmo.lab5.client.controller.auth.api.StoredJWTCredentials;
import ru.bardinpetr.itmo.lab5.clientgui.api.APIConnectorFactory;
import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelProvider;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.WorkersTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.remove.greater.WorkerRemoveGFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandManager;
import ru.bardinpetr.itmo.lab5.common.error.APIClientException;
import ru.bardinpetr.itmo.lab5.models.commands.api.ClearCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.AuthCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.PasswordLoginCommand;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.DefaultAuthenticationCredentials;
import ru.bardinpetr.itmo.lab5.models.commands.auth.models.JWTLoginResponse;

import javax.swing.*;
import java.awt.*;

import static javax.swing.SwingUtilities.invokeLater;

public class WorkerShowPanelZ extends ResourcedPanel {
    private final JPanel workerTablePanel;
    private JButton openAddWorkerPlane;
    private JButton clearWorkerButton;
    private JButton removeGreaterButton;

    public WorkerShowPanelZ() {
        workerTablePanel = new WorkersTable(ModelProvider.getInstance().workers());

        initComponents();
        setVisible(true);
    }


    public static void main(String[] args) throws APIClientException {
        UIResources.getInstance();
        APIConnectorFactory.create();
        var loginCmd = new PasswordLoginCommand();
        loginCmd.setCredentials(new DefaultAuthenticationCredentials("u", "p"));
        APIProvider.getCredentialsStorage().setCredentials(
                new StoredJWTCredentials((JWTLoginResponse) (
                        (AuthCommand.AuthCommandResponse) APIProvider.getConnector().call(loginCmd)).getData()
                )
        );

        var f = new JFrame();
        f.setContentPane(new WorkerShowPanelZ());
        f.setSize(700, 500);
        f.setVisible(true);
    }

    protected void initComponents() {
        openAddWorkerPlane = new JButton();
        clearWorkerButton = new JButton();
        removeGreaterButton = new JButton();

        setLayout(new BorderLayout());

        add(workerTablePanel, BorderLayout.CENTER);

        var panel1 = new Box(BoxLayout.LINE_AXIS);
        panel1.add(Box.createGlue());
        panel1.add(clearWorkerButton);
        panel1.add(removeGreaterButton);
        add(panel1, BorderLayout.SOUTH);

        openAddWorkerPlane.addActionListener((e -> invokeLater(WorkerAddFrameZ::new)));
        clearWorkerButton.addActionListener(
                (e) -> invokeLater(
                        () -> APICommandManager.getInstance().sendCommand(
                                new ClearCommand(),
                                this,
                                "WorkerShowPanel.canNotClear.text",
                                (response) -> {
                                }
                        )
                )
        );
        removeGreaterButton.addActionListener((e -> new WorkerRemoveGFrame()));

        initComponentsI18n();

    }

    @Override
    protected void initComponentsI18n() {
        UIResources resources = getResources();
        openAddWorkerPlane.setText(resources.get("WorkerShowPanel.openAddWorkerPlane.text"));
        clearWorkerButton.setText(resources.get("WorkerShowPanel.clearWorkerButton.text"));
        removeGreaterButton.setText(resources.get("WorkerShowPanel.removeGreaterButton.text"));

    }
}
