package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerHeaderPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveByIdCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.UpdateCommand;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.SwingUtilities.invokeLater;

public class WorkerUpdateFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;

    private JButton updateWorkerButton;
    private JButton workerUpdateCancelButton;
    private Worker defaultWorker;
    private boolean isEditable;
    private JButton deleteWorkerButton;
    private WorkerHeaderPanel workerHeaderPanel;

    public WorkerUpdateFrameZ(Worker defaultWorker, boolean isChangeable) {
        this.isEditable = isChangeable;
        this.defaultWorker = defaultWorker;
        initComponents();
        setResizable(false);
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(defaultWorker, isEditable);
        workerHeaderPanel = new WorkerHeaderPanel(defaultWorker);
        updateWorkerButton = new JButton();
        deleteWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();


        setLayout(new GridBagLayout());

        var headerConstrains = GridConstrains.placedAdd(0,0);
        headerConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerHeaderPanel, headerConstrains);
        var infoConstrains = GridConstrains.placedAdd(0,1);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(updateWorkerButton, GridConstrains.placedAdd(0, 2));
        add(deleteWorkerButton, GridConstrains.placedAdd(1, 2));
        add(workerUpdateCancelButton, GridConstrains.placedAdd(2, 2));

        updateWorkerButton.setEnabled(isEditable);

        workerUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        deleteWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new APICommandMenger().sendCommand(
                        new RemoveByIdCommand(
                                defaultWorker.getId()
                        ),
                        workerInfoPanel,
                        "WorkerUpdateFrameZ.deleteFailed.text",
                        (e2) -> {}
                );
                dispose();
            }
        });
        updateWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                invokeLater(() ->{
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
                });

            }
        });

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());



    }
    @Override
    protected void initComponentsI18n() {
        var bundle = getResources();
        setTitle(bundle.getString("WorkerUpdateFrameZ.title"));
        updateWorkerButton.setText(bundle.getString("WorkerUpdateFrameZ.updateWorkerButton.text"));
        deleteWorkerButton.setText(bundle.getString("WorkerUpdateFrame.deleteWorkerButton.text"));
        workerUpdateCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
    }
}
