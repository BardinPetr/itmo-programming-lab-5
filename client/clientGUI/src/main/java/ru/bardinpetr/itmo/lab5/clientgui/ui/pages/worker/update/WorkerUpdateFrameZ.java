package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkerUpdateFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;

    private JButton updateWorkerButton;
    private JButton workerUpdateCancelButton;
    private Worker defaultWorker;
    private boolean isEditable;
    public WorkerUpdateFrameZ(Worker defaultWorker, boolean isChangeable) {
        this.isEditable = isChangeable;
        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(defaultWorker, isEditable);
        updateWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();


        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0,0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(updateWorkerButton, GridConstrains.placedAdd(0, 1));
        add(workerUpdateCancelButton, GridConstrains.placedAdd(1, 1));

        updateWorkerButton.setEnabled(isEditable);

        workerUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        updateWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(
                            workerInfoPanel,
                            getResources().getString(worker.msg),
                            getResources().getString("AddFrame.input.error.text"),
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    new APICommandMenger().sendCommand(
                            new UpdateCommand(worker.data.getId(), worker.data),
                            workerInfoPanel,
                            "WorkerUpdateFrameZ.updateFailed.text",
                            (e1) -> {}
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
        var bundle = getResources();
        updateWorkerButton.setText(bundle.getString("WorkerUpdateFrameZ.updateWorkerButton.text"));
        workerUpdateCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
    }
}
