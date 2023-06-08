package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMaxCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

public class WorkerAddFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;
    private ResourceBundle bundle = getResources();
    private JRadioButton nornalAdd;
    private JButton addWorkerButton;
    private JRadioButton ifMaxAdd;
    private JRadioButton ifMinAdd;
    private JButton workerAddCancelButton;
    private AddType addType = AddType.Normal;

    public WorkerAddFrameZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ(null);
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

        nornalAdd.addActionListener((e -> addType = AddType.Normal));
        ifMaxAdd.addActionListener((e -> addType = AddType.addMax));
        ifMinAdd.addActionListener((e -> addType = AddType.addMin));
        workerAddCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        addWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(workerInfoPanel, bundle.getString(worker.msg), bundle.getString("AddFrame.input.error.text"), JOptionPane.ERROR_MESSAGE);
                }
                else {
                    APICommand command;
                    if (addType.equals(AddType.Normal)){
                        command = new AddCommand(worker.data);
                        //TODO normal add worker command
                    } else if (addType.equals(AddType.addMax)){
                        //TODO addIfMax worker command
                    } else {
                        //TODO addIfMin worker command
                    }
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
        nornalAdd.setText(bundle.getString("WorkerAddFrame.nornalAdd.text"));
        addWorkerButton.setText(bundle.getString("WorkerAddFrame.addWorkerButton.text"));
        ifMaxAdd.setText(bundle.getString("WorkerAddFrame.ifMaxAdd.text"));
        ifMinAdd.setText(bundle.getString("WorkerAddFrame.ifMainAdd.text"));
        workerAddCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));
    }
}

enum AddType {
    Normal(), addMax(), addMin();
    AddType(){}
}
