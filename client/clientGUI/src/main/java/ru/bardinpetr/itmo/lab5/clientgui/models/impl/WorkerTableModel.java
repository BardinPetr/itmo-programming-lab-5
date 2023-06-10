package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerTableModel extends TableListModelAdapter<Worker, WorkerModel> {
    private final Set<String> lockedColumns = new HashSet<>();

    public WorkerTableModel(WorkerModel model) {
        super(model, List.of("ID", "Owner", "Name"));
        lockedColumns.addAll(getColumnNames());
    }

    @Override
    protected Object getValueAt(Worker object, int column) {
        return List.of(
                object.getId(),
                object.getOwnerUsername(),
                object.getName()
        ).get(column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        var isUnlocked = !lockedColumns.contains(getColumnName(column));
        var isOwned = getBaseModel().isEditableByCurrentUser(getBaseModel().get(row));
        return isUnlocked && isOwned;
    }
}

