package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.OrganizationTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.XTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.organization.add.OrgAddFrameZ;

import java.awt.event.ActionEvent;

public class OrganizationTable extends XTable {

    private final OrganizationModel baseModel;

    public OrganizationTable(OrganizationModel model) {
        super(new OrganizationTableModel(model));
        this.baseModel = model;
    }

    @Override
    protected void initButtonBlock() {
        super.initButtonBlock();
        updateButton.setVisible(false);
        deleteButton.setVisible(false);
    }

    @Override
    protected void onDelete(ActionEvent actionEvent) {
        var selected = getSelectedIndexes();
        if (selected.size() != 1)
            return;
        var data = baseModel.get(selected.get(0));

        // TODO add delete
        System.err.println("NO DELETE IMPLEMENTATION");
    }

    @Override
    protected void onUpdate(ActionEvent event) {
        var selected = getSelectedIndexes();
        if (selected.size() != 1)
            return;
        var data = baseModel.get(selected.get(0));

        // TODO add update
        System.err.println("NO UPDATE IMPLEMENTATION");
    }

    @Override
    protected void onInsert(ActionEvent event) {
        new OrgAddFrameZ();
    }
}
