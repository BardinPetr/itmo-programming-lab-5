package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.remove.greater;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandManager;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveGreaterCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkerRemoveGFrame extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;

    private JButton removeWorkerButton;
    private JButton workerUpdateCancelButton;

    public WorkerRemoveGFrame() {
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    protected void initComponents() {
        workerInfoPanel = new WorkerInfoPanelZ();
        removeWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();


        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0, 0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(removeWorkerButton, GridConstrains.placedAdd(0, 1));
        add(workerUpdateCancelButton, GridConstrains.placedAdd(1, 1));


        workerUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        removeWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(workerInfoPanel, getResources().get(worker.msg), getResources().get("AddFrame.input.error.text"), JOptionPane.ERROR_MESSAGE);
                } else {
                    APICommandManager.getInstance().sendCommand(
                            new RemoveGreaterCommand(worker.data),
                            workerInfoPanel,
                            "WorkerRemoveGFrame.removeGreaterWorkerButton.error.text",
                            (response) -> {
                            }
                    );
                }
            }
        });

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());
    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        setTitle(resources.get("WorkerRemoveGFrame.title"));
        removeWorkerButton.setText(resources.get("WorkerRemoveGFrame.removeGreaterWorkerButton.text"));
        workerUpdateCancelButton.setText(resources.get("WorkerAddFrame.workerAddCancelButton.text"));
    }
}
