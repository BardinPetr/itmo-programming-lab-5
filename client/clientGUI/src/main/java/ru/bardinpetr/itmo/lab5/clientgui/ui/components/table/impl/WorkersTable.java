package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.XTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerUpdateFrameFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;

import java.awt.event.ActionEvent;

public class WorkersTable extends XTable {

    private final WorkerModel baseModel;

    public WorkersTable(WorkerModel model) {
        super(new WorkerTableModel(model));
        this.baseModel = model;
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

        WorkerUpdateFrameFactory
                .getInstance()
                .open(data, baseModel.isEditableByCurrentUser(data));
    }

    @Override
    protected void onInsert(ActionEvent event) {
        new WorkerAddFrameZ();
    }
}
