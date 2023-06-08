package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class WorkerAddFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;
    private JPanel panel1;
    private JRadioButton nornalAdd;
    private JButton addWorkerButton;
    private JRadioButton ifMaxAdd;
    private JRadioButton ifMinAdd;
    private JButton workerAddCancelButton;

    public WorkerAddFrameZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(new Worker());
        panel1 = new JPanel();
        nornalAdd = new JRadioButton();
        addWorkerButton = new JButton();
        ifMaxAdd = new JRadioButton();
        ifMinAdd = new JRadioButton();
        workerAddCancelButton = new JButton();

        nornalAdd.setSelected(true);

        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0,0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(addWorkerButton, GridConstrains.placedAdd(0, 2));
        add(nornalAdd, GridConstrains.placedAdd(1, 1));
        add(ifMaxAdd, GridConstrains.placedAdd(1, 2));
        add(ifMinAdd, GridConstrains.placedAdd(1, 3));
        add(workerAddCancelButton, GridConstrains.placedAdd(0, 4));

        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(nornalAdd);
        buttonGroup1.add(ifMaxAdd);
        buttonGroup1.add(ifMinAdd);

        workerAddCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        addWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(workerInfoPanel.getWorker());
            }
        });

        initComponentsI18n();
        pack();
        setLocationRelativeTo(getOwner());



    }
    @Override
    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        nornalAdd.setText(bundle.getString("WorkerAddFrame.nornalAdd.text"));
        addWorkerButton.setText(bundle.getString("WorkerAddFrame.addWorkerButton.text"));
        ifMaxAdd.setText(bundle.getString("WorkerAddFrame.ifMaxAdd.text"));
        ifMinAdd.setText(bundle.getString("WorkerAddFrame.ifMainAdd.text"));
        workerAddCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }
}
