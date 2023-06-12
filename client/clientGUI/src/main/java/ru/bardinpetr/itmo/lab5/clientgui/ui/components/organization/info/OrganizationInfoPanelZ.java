package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.NameField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.OrganizationTypeCombobox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;

import javax.swing.*;
import java.awt.*;

public class OrganizationInfoPanelZ extends ResourcedPanel {
    private final Organization defaultOrganization;
    UIResources resources = getResources();
    private JLabel label1;
    private NameField nameField;
    private JLabel label2;
    private OrganizationTypeCombobox organizationTypeCombobox;


    public OrganizationInfoPanelZ(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();

        nameField = new NameField((s) -> {
        });

        organizationTypeCombobox = new OrganizationTypeCombobox((e -> {
        }));

        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 300, 0};
        add(label1, GridConstrains.placedAdd(0, 0));
        add(label2, GridConstrains.placedAdd(0, 1));
        add(nameField, GridConstrains.placedAdd(1, 0));
        add(organizationTypeCombobox, GridConstrains.placedAdd(1, 1));

        if (defaultOrganization != null) {
            nameField.setName(defaultOrganization.getFullName());
            organizationTypeCombobox.setData(new EnumPresenter<>(defaultOrganization.getType()));
        }

        initComponentsI18n();
    }

    public DataContainer<Organization> getOrganization() {
        var dataContainer = new DataContainer<Organization>(
                true,
                new Organization(),
                ""
        );
        var name = nameField.getData();
        var type = organizationTypeCombobox.getData();

        if (!name.isAllowed) dataContainer.copyMeta(name);
        if (!type.isAllowed) dataContainer.copyMeta(type);

        dataContainer.data.setFullName(name.data);
        dataContainer.data.setType((OrganizationType) type.data.getEnumData());
        return dataContainer;

    }


    @Override
    protected void initComponentsI18n() {
        resources = getResources();

        label1.setText(resources.get("OrganizationValidator.label1.text"));
        label2.setText(resources.get("OrganizationValidator.label2.text"));
    }

}
