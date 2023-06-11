package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.DateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.LocalDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.NullableDatePanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info.WorkerInfoPanelZ;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WorkerTableModel extends TableListModelAdapter<Worker, WorkerModel> {
    private final Set<String> lockedColumns = new HashSet<>();

    public WorkerTableModel(WorkerModel model) {
        super(model, List.of(
                UIResources.getInstance().get("WorkerTableModel.Column.ID.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Owner.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Creation date.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Name.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Salary.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.StartDate.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.EndDate.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.XCoordinate.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.YCoordinate.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Organization.text"),
                UIResources.getInstance().get("WorkerTableModel.Column.Position.text")
                )
        );
        lockedColumns.addAll(getColumnNames());
    }

    @Override
    protected Object getValueAt(Worker object, int column) {
        var startDate = new DateField((e) -> {});
        startDate.setData(object.getStartDate());
        var endDate = new NullableDatePanel((e) -> {});
        endDate.setData(object.getEndDate());
        var creationDate = new LocalDateField((e) -> {});
        creationDate.setData(object.getCreationDate().toLocalDate());
        return List.of(
                object.getId(),
                object.getOwnerUsername(),
                creationDate.getText(),
                object.getName(),
                object.getSalary(),
                startDate.getText(),
                endDate.getText(),
                object.getCoordinates().getX(),
                object.getCoordinates().getY(),
                new OrganizationPresenter(
                        object.getOrganization()
                ),
                new EnumPresenter<>(
                        object.getPosition()
                )
        ).get(column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        var isUnlocked = !lockedColumns.contains(getColumnName(column));
        var isOwned = getBaseModel().isEditableByCurrentUser(getBaseModel().get(row));
        return isUnlocked && isOwned;
    }
}

