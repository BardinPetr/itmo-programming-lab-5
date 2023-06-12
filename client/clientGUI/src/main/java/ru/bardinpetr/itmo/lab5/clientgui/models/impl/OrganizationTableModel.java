package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.List;

public class OrganizationTableModel extends TableListModelAdapter<Organization, OrganizationModel> {

    public OrganizationTableModel(OrganizationModel model) {
        super(model, 3);
        UIResources
                .getInstance()
                .addLocaleChangeListener(e -> updateColumnNames());
    }

    @Override
    protected Object getValueAt(Organization object, int column) {
        return List.of(
                object.getId(),
                object.getFullName(),
                object.getType()
        ).get(column);
    }

    @Override
    protected List<String> getColumnNames() {
        return List.of("ID", "Name", "Type");
    }
}

