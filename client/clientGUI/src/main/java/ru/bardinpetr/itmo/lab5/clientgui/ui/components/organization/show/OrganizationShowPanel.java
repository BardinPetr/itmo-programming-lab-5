package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.show;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.models.factory.ModelProvider;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl.OrganizationTable;

import javax.swing.*;
import java.awt.*;

public class OrganizationShowPanel extends ResourcedPanel {
    private final JPanel orgTablePanel;
    private JButton clearOrgButton;

    public OrganizationShowPanel() {
        orgTablePanel = new OrganizationTable(ModelProvider.getInstance().organizations());

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
        UIResources resources = getResources();
        clearOrgButton.setText(resources.get("WorkerShowPanel.clearWorkerButton.text"));
    }
}
