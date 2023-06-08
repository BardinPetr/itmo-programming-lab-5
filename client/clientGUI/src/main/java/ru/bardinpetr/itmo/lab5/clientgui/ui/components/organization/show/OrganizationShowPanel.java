package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add.OrgAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class OrganizationShowPanel extends ResourcedPanel {
    private JPanel orgTablePanel;
    private JPanel panel1;
    private JButton openAddOrgButton;
    private JButton clearOrgButton;
    public OrganizationShowPanel() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        orgTablePanel = new JPanel();
        panel1 = new JPanel();
        openAddOrgButton = new JButton();
        clearOrgButton = new JButton();

        setLayout(new BorderLayout());
        add(orgTablePanel, BorderLayout.CENTER);

        panel1.setLayout(new GridBagLayout());
        panel1.add(openAddOrgButton);
        panel1.add(clearOrgButton);
        add(panel1, BorderLayout.PAGE_END);

        openAddOrgButton.addActionListener((e -> new OrgAddFrameZ()));

        clearOrgButton.setVisible(false);

        initComponentsI18n();

    }
    @Override
    protected void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        openAddOrgButton.setText(bundle.getString("OrganizationShowPanel.openAddWorkerPlane.text"));
        clearOrgButton.setText(bundle.getString("WorkerShowPanel.clearWorkerButton.text"));

    }
}
