package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class WorkerShowPanelZ extends ResourcedPanel {
    private JPanel workerTablePanel;
    private JPanel panel1;
    private JButton openAddWorkerPlane;
    private JButton clearWorkerButton;
    private JButton removeGreaterButton;
    public WorkerShowPanelZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        workerTablePanel = new JPanel();
        panel1 = new JPanel();
        openAddWorkerPlane = new JButton();
        clearWorkerButton = new JButton();
        removeGreaterButton = new JButton();

        setLayout(new BorderLayout());
        add(workerTablePanel, BorderLayout.CENTER);

        panel1.setLayout(new GridBagLayout());
        panel1.add(openAddWorkerPlane);
        panel1.add(clearWorkerButton);
        panel1.add(removeGreaterButton);
        add(panel1, BorderLayout.PAGE_END);

        openAddWorkerPlane.addActionListener((e -> new WorkerAddFrameZ()));

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
