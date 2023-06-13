package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import javax.swing.*;
import java.awt.*;

public class OrganizationHeaderPanel extends ResourcedPanel {
    private final Organization defaultOrganization;
    UIResources resources = getResources();
    private JLabel idLabel;
    private JTextField idField;

    public OrganizationHeaderPanel(Organization organization) {

        this.defaultOrganization = organization;
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        idLabel = new JLabel();

        idField = new JTextField();




        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 300, 0};
        add(idLabel, GridConstrains.placedAdd(0, 0));

        add(idField, GridConstrains.placedAdd(1, 0));

        idField.setEditable(false);

        if (defaultOrganization != null) {
            idField.setText(String.valueOf(defaultOrganization.getId()));
        }
        initComponentsI18n();
    }


    @Override
    protected void initComponentsI18n() {
        resources = getResources();
        idLabel.setText(resources.get("OrganizationHeaderPanel.idLabel.text"));
    }

}
