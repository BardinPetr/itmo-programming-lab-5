package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show;

import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelProvider;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.WorkersTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.remove.greater.WorkerRemoveGFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.models.commands.api.ClearCommand;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

import static javax.swing.SwingUtilities.invokeLater;

public class WorkerShowPanelZ extends ResourcedPanel {
    private final JPanel workerTablePanel;
    private JButton openAddWorkerPlane;
    private JButton clearWorkerButton;
    private JButton removeGreaterButton;

    public WorkerShowPanelZ() {
        workerTablePanel = new WorkersTable(ModelProvider.workers());

        initComponents();
        setVisible(true);
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
        clearWorkerButton.addActionListener((e) -> invokeLater(() -> new APICommandMenger().sendCommand(
                new ClearCommand(),
                this,
                "WorkerShowPanel.canNotClear.text",
                (response) -> {
                }
        )));
        removeGreaterButton.addActionListener((e -> new WorkerRemoveGFrame()));

        initComponentsI18n();

    }

    @Override
    protected void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        openAddWorkerPlane.setText(bundle.getString("WorkerShowPanel.openAddWorkerPlane.text"));
        clearWorkerButton.setText(bundle.getString("WorkerShowPanel.clearWorkerButton.text"));
        removeGreaterButton.setText(bundle.getString("WorkerShowPanel.removeGreaterButton.text"));

    }
}
