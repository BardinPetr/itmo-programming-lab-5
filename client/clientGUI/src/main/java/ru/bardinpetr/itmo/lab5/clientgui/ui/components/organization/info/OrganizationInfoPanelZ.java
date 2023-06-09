package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.NameField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.OrganizationTypeCombobox;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class OrganizationInfoPanelZ extends ResourcedPanel {
    private JLabel label1;
    private NameField nameField;
    private JLabel label2;
    private OrganizationTypeCombobox organizationTypeCombobox;
    private Organization defaultOrganization;
    ResourceBundle bundle = getResources();


    public OrganizationInfoPanelZ(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        label1 = new JLabel();
        label2 = new JLabel();

        nameField = new NameField((s)-> {});

        organizationTypeCombobox = new OrganizationTypeCombobox((e -> {}));

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 300, 0};
        add(label1, GridConstrains.placedAdd(0,0));
        add(label2, GridConstrains.placedAdd(0, 1));
        add(nameField, GridConstrains.placedAdd(1, 0));
        add(organizationTypeCombobox, GridConstrains.placedAdd(1, 1));

        if (defaultOrganization!=null){
            nameField.setName(defaultOrganization.getFullName());
            organizationTypeCombobox.setData(defaultOrganization.getType());
        }

        initComponentsI18n();
    }

    public DataContainer<Organization> getOrganization(){
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
        dataContainer.data.setType(type.data);
        return dataContainer;

    }




    @Override
    protected void initComponentsI18n() {
        bundle = getResources();

        label1.setText(bundle.getString("OrganizationValidator.label1.text"));
        label2.setText(bundle.getString("OrganizationValidator.label2.text"));
    }

}
