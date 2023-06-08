package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.update;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class WorkerUpdateFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;
    private ResourceBundle bundle = getResources();

    private JButton updateWorkerButton;
    private JButton workerUpdateCancelButton;
    private Worker defaultWorker;

    public WorkerUpdateFrameZ(Worker defaultWorker) {
        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(defaultWorker);
        JPanel panel1 = new JPanel();
        updateWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();


        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0,0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(updateWorkerButton, GridConstrains.placedAdd(0, 1));
        add(workerUpdateCancelButton, GridConstrains.placedAdd(1, 1));


        workerUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        updateWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(workerInfoPanel, bundle.getString(worker.msg), "Aaa", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    System.out.println(worker);
                }
            }
        });

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());



    }
    @Override
    protected void initComponentsI18n() {
        bundle = getResources();
        updateWorkerButton.setText(bundle.getString("WorkerUpdateFrameZ.updateWorkerButton.text"));
        workerUpdateCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
    }
}
