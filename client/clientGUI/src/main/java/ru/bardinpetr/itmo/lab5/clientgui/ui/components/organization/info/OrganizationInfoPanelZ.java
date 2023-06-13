package ru.bardinpetr.itmo.lab5.clientgui.ui.components.organization.info;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.*;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.NullableDatePanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.*;
import ru.bardinpetr.itmo.lab5.models.data.validation.OrganizationValidator;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidator;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static javax.swing.SwingUtilities.invokeLater;

public class OrganizationInfoPanelZ extends ResourcedPanel {
    private final Organization defaultOrganization;

    UIResources resources = getResources();
    private JLabel label1;
    private NameField workerNameField;
    private JLabel label2;
    private OrganizationTypeCombobox typeCombobox;
    private DataContainer<Organization> workerDataContainer;

    public OrganizationInfoPanelZ() {
        this(null);
    }

    public OrganizationInfoPanelZ(Organization defaultOrganization) {
        this.defaultOrganization = defaultOrganization;
        invokeLater(() ->initComponents());
        setVisible(true);
        setPreferredSize(new Dimension(400, 50));

        this.workerDataContainer = new DataContainer<>(
                defaultOrganization,
                OrganizationValidator.validateAll(defaultOrganization)
        );
    }

    protected void initComponents() {
        label1 = new JLabel();
        label2 = new JLabel();

        setLayout(new GridBagLayout());

        workerNameField = new NameField((s) -> {
        });
        typeCombobox = new OrganizationTypeCombobox((e) -> {});

        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 300, 0};
        add(label1, GridConstrains.placedAdd(0, 0));
        add(label2, GridConstrains.placedAdd(0, 1));

        add(workerNameField, GridConstrains.placedAdd(1, 0));
        add(typeCombobox, GridConstrains.placedAdd(1, 1));

        setData(defaultOrganization);

        initComponentsI18n();
    }

    public void setData(Organization organization) {
        if (organization != null) {
            workerNameField.setData(organization.getFullName());
            typeCombobox.setData(new EnumPresenter(organization.getType()));
        }
    }

    public DataContainer<Organization> getOrganization() {
        workerDataContainer = new DataContainer<>(
                true,
                Objects.requireNonNullElseGet(defaultOrganization, Organization::new),
                ""
        );
        var name = workerNameField.getData();
        var type = typeCombobox.getData();

        if (!name.isAllowed) workerDataContainer.copyMeta(name);
        if (!type.isAllowed) workerDataContainer.copyMeta(type);

        if (!workerDataContainer.isAllowed) {
            workerDataContainer.data = null;
            return workerDataContainer;
        }
        workerDataContainer.data.setFullName(name.data);
        workerDataContainer.data.setType((OrganizationType) type.data.getEnumData());
        return workerDataContainer;

    }


    @Override
    protected void initComponentsI18n() {
        resources = getResources();

        label1.setText(resources.get("OrganizationValidator.label1.text"));
        label2.setText(resources.get("OrganizationValidator.label2.text"));    }

}
