package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Organization;

import java.util.List;

public class OrganizationTableModel extends TableListModelAdapter<Organization, OrganizationModel> {

    private static final List<String> columnNameKeys = List.of(
            "OrganizationTableModel.Column.ID.text",
            "OrganizationTableModel.Column.Name.text",
            "OrganizationTableModel.Column.Type.text"
    );

    public OrganizationTableModel(OrganizationModel model) {
        super(model, columnNameKeys.size());
        UIResources
                .getInstance()
                .addLocaleChangeListener(e -> updateColumnNames());
    }

    @Override
    protected Object getValueAt(Organization object, int column) {
        return List.of(
                object.getId(),
                object.getFullName(),
                new EnumPresenter<>(object.getType())
        ).get(column);
    }

    @Override
    protected List<String> getColumnNames() {
        return columnNameKeys
                .stream()
                .map(UIResources.getInstance()::get)
                .toList();
    }
}

