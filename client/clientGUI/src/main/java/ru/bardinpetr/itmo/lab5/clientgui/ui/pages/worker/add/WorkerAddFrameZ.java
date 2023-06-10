package ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedFrame;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMaxCommand;
import ru.bardinpetr.itmo.lab5.models.commands.api.AddIfMinCommand;
import ru.bardinpetr.itmo.lab5.models.commands.requests.APICommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import static javax.swing.SwingUtilities.invokeLater;
import static ru.bardinpetr.itmo.lab5.models.commands.api.AddCommand.*;

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
        setVisible(true);
    }

    protected void initComponents(){
        workerInfoPanel = new WorkerInfoPanelZ();
        normalAdd = new JRadioButton();
        addWorkerButton = new JButton();
        ifMaxAdd = new JRadioButton();
        ifMinAdd = new JRadioButton();
        workerAddCancelButton = new JButton();

        normalAdd.setSelected(true);

        setLayout(new GridBagLayout());

        var infoConstrains = GridConstrains.placedAdd(0,0);
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
                var worker = workerInfoPanel.getWorker();
                if (!worker.isAllowed) {
                    JOptionPane.showMessageDialog(
                            workerInfoPanel,
                            getResources().getString(worker.msg),
                            getResources().getString("AddFrame.input.error.text"),
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                else {
                    AddCommand command;
                    if (normalAdd.isSelected()){
                        command = new AddCommand();
                    } else if (ifMaxAdd.isSelected()){
                        command = new AddIfMaxCommand();
                    } else {
                        command = new AddIfMinCommand();
                    }
                    command.setElement(worker.data);
                    new APICommandMenger().sendCommand(
                            command,
                            workerInfoPanel,
                            "MainFrame.canNotAddMsg.text",
                            (response) -> {
                                var resp = (AddCommandResponse) response;
                                JOptionPane.showConfirmDialog(
                                        workerInfoPanel,
                                        getResources().getString("WorkerAddFrame.newIdMsg")+
                                                ": " + resp.getId(),
                                        getResources().getString("WorkerAddFrame.newIdMsg")
                                        , JOptionPane.PLAIN_MESSAGE
                                );
                            }
                    );
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
        var bundle = getResources();
        setTitle(bundle.getString("WorkerAddFrame.title"));
        normalAdd.setText(bundle.getString("WorkerAddFrame.nornalAdd.text"));
        addWorkerButton.setText(bundle.getString("WorkerAddFrame.addWorkerButton.text"));
        ifMaxAdd.setText(bundle.getString("WorkerAddFrame.ifMaxAdd.text"));
        ifMinAdd.setText(bundle.getString("WorkerAddFrame.ifMainAdd.text"));
        workerAddCancelButton.setText(bundle.getString("WorkerAddFrame.workerAddCancelButton.text"));

    }
}


