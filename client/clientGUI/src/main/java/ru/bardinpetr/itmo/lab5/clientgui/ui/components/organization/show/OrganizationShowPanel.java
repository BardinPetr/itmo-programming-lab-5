package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show;

import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.OrganizationTable;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class OrganizationShowPanel extends ResourcedPanel {
    private final JPanel orgTablePanel;
    private JButton clearOrgButton;

    public OrganizationShowPanel() {
        orgTablePanel = new OrganizationTable(ModelFactory.createOrganizations());

        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        JPanel panel1 = new JPanel();
        clearOrgButton = new JButton();

        setLayout(new BorderLayout());
        add(orgTablePanel, BorderLayout.CENTER);

        panel1.setLayout(new GridBagLayout());
        panel1.add(clearOrgButton);
        add(panel1, BorderLayout.PAGE_END);

        clearOrgButton.setVisible(false);

        initComponentsI18n();

    }

    @Override
    protected void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        clearOrgButton.setText(bundle.getString("WorkerShowPanel.clearWorkerButton.text"));
    }
}
