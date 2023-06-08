package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.remove.greater;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class WorkerRemoveGFrame extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;
    private ResourceBundle bundle = getResources();

    private JButton removeWorkerButton;
    private JButton workerUpdateCancelButton;

    public WorkerRemoveGFrame() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(null);
        JPanel panel1 = new JPanel();
        removeWorkerButton = new JButton();
        workerUpdateCancelButton = new JButton();


        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0,0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(removeWorkerButton, GridConstrains.placedAdd(0, 1));
        add(workerUpdateCancelButton, GridConstrains.placedAdd(1, 1));


        workerUpdateCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        removeWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(workerInfoPanel, bundle.getString(worker.msg), "Aaa", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    //TODO send removeGreater command
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
        removeWorkerButton.setText(bundle.getString("WorkerRemoveGFrame.removeGreaterWorkerButton.text"));
        workerUpdateCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
    }
}
