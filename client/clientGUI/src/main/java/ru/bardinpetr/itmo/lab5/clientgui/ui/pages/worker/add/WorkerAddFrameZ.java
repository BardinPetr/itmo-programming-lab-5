package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMaxCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMinCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static javax.swing.SwingUtilities.invokeLater;
import static ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand.AddCommandResponse;

public class WorkerAddFrameZ extends ResourcedFrame {
    private WorkerInfoPanelZ workerInfoPanel;
    private JRadioButton normalAdd;
    private JButton addWorkerButton;
    private JRadioButton ifMaxAdd;
    private JRadioButton ifMinAdd;
    private JButton workerAddCancelButton;

    public WorkerAddFrameZ() {
        UIResources.getInstance().addLocaleChangeListener((e -> initComponentsI18n()));

        initComponents();
        setResizable(false);
        setVisible(true);
    }

    protected void initComponents() {
        workerInfoPanel = new WorkerInfoPanelZ();
        normalAdd = new JRadioButton();
        addWorkerButton = new JButton();
        ifMaxAdd = new JRadioButton();
        ifMinAdd = new JRadioButton();
        workerAddCancelButton = new JButton();

        normalAdd.setSelected(true);

        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0, 0);
        infoConstrains.gridwidth = GridBagConstraints.REMAINDER;
        add(workerInfoPanel, infoConstrains);
        add(addWorkerButton, GridConstrains.placedAdd(0, 2));
        add(normalAdd, GridConstrains.placedAdd(1, 1));
        add(ifMaxAdd, GridConstrains.placedAdd(1, 2));
        add(ifMinAdd, GridConstrains.placedAdd(1, 3));
        add(workerAddCancelButton, GridConstrains.placedAdd(0, 4));

        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(normalAdd);
        buttonGroup1.add(ifMaxAdd);
        buttonGroup1.add(ifMinAdd);


        workerAddCancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose();
            }
        });
        addWorkerButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                invokeLater(() -> {
                    var worker = workerInfoPanel.getWorker();
                    if (!worker.isAllowed) {
                        JOptionPane.showMessageDialog(
                                workerInfoPanel,
                                getResources().get(worker.msg),
                                getResources().get("AddFrame.input.error.text"),
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        AddCommand command;
                        if (normalAdd.isSelected()) {
                            command = new AddCommand();
                        } else if (ifMaxAdd.isSelected()) {
                            command = new AddIfMaxCommand();
                        } else {
                            command = new AddIfMinCommand();
                        }
                        command.setElement(worker.data);
                        APICommandMenger.getInstance().sendCommand(
                                command,
                                workerInfoPanel,
                                "MainFrame.canNotAddMsg.text",
                                (response) -> {
                                    var resp = (AddCommandResponse) response;
                                    JOptionPane.showConfirmDialog(
                                            workerInfoPanel,
                                            getResources().get("WorkerAddFrame.newIdMsg") +
                                                    ": " + resp.getId(),
                                            getResources().get("WorkerAddFrame.newIdMsg")
                                            , JOptionPane.PLAIN_MESSAGE
                                    );
                                }
                        );
                        System.out.println(worker);
                    }
                });
            }
        });

        initComponentsI18n();
        pack();
//        System.out.println(workerInfoPanel.getSize());
        setLocationRelativeTo(getOwner());


    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        setTitle(resources.get("WorkerAddFrame.title"));
        normalAdd.setText(resources.get("WorkerAddFrame.nornalAdd.text"));
        addWorkerButton.setText(resources.get("WorkerAddFrame.addWorkerButton.text"));
        ifMaxAdd.setText(resources.get("WorkerAddFrame.ifMaxAdd.text"));
        ifMinAdd.setText(resources.get("WorkerAddFrame.ifMainAdd.text"));
        workerAddCancelButton.setText(resources.get("WorkerAddFrame.workerAddCancelButton.text"));

    }
}


