package ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.impl;

import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerModel;
import ru.bardinpetr.itmo.lab5.clientgui.models.impl.WorkerTableModel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.XTable;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.show.WorkerUpdateFrameFactory;
import ru.bardinpetr.itmo.lab5.clientgui.ui.pages.worker.add.WorkerAddFrameZ;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.APICommandManager;
import ru.bardinpetr.itmo.lab5.models.commands.api.RemoveByIdCommand;

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
        APICommandManager.getInstance().sendCommand(
                new RemoveByIdCommand(
                        data.getId()
                ),
                this,
                "WorkerUpdateFrameZ.deleteFailed.text",
                (e2) -> {
                }
        );
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
