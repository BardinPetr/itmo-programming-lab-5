package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.XTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add.OrgAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.update.OrgUpdateFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandMenger;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveOrganizationCommand;

import java.awt.event.ActionEvent;

public class OrganizationTable extends XTable {

    private final OrganizationModel baseModel;

    public OrganizationTable(OrganizationModel model) {
        super(new OrganizationTableModel(model));
        this.baseModel = model;
    }

    @Override
    protected void onDelete(ActionEvent actionEvent) {
        var selected = getSelectedIndexes();
        if (selected.size() != 1)
            return;
        var data = baseModel.get(selected.get(0));
        APICommandMenger.getInstance().sendCommand(
                new RemoveOrganizationCommand(data.getId()),
                null,
                "OrganizationTable.deleteError.text",
                (e) -> {
                }

        );
    }

    @Override
    protected void onUpdate(ActionEvent event) {
        var selected = getSelectedIndexes();
        if (selected.size() != 1)
            return;
        var data = baseModel.get(selected.get(0));
        new OrgUpdateFrameZ(data);
    }

    @Override
    protected void onInsert(ActionEvent event) {
        new OrgAddFrameZ();
    }
}
