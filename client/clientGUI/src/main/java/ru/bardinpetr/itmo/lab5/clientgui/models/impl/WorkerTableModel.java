package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.DateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.LocalDateField;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.NullableDatePanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.text.DateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class WorkerTableModel extends TableListModelAdapter<Worker, WorkerModel> {
    private static final List<String> columnNameKeys = List.of(
            "WorkerTableModel.Column.ID.text",
            "WorkerTableModel.Column.Owner.text",
            "WorkerTableModel.Column.Creation date.text",
            "WorkerTableModel.Column.Name.text",
            "WorkerTableModel.Column.Salary.text",
            "WorkerTableModel.Column.StartDate.text",
            "WorkerTableModel.Column.EndDate.text",
            "WorkerTableModel.Column.XCoordinate.text",
            "WorkerTableModel.Column.YCoordinate.text",
            "WorkerTableModel.Column.Organization.text",
            "WorkerTableModel.Column.Position.text"
    );

    private final Set<String> lockedColumns = new HashSet<>();

    public WorkerTableModel(WorkerModel model) {
        super(model, columnNameKeys.size());
        lockedColumns.addAll(getColumnNames());
        UIResources
                .getInstance()
                .addLocaleChangeListener(e -> updateColumnNames());
    }

    @Override
    protected Object getValueAt(Worker object, int column) {
        var timeFormat = DateFormat
                .getDateTimeInstance(
                        DateFormat.MEDIUM,
                        DateFormat.MEDIUM,
                        Locale.getDefault()
                );
        var dateFormat = DateFormat
                .getDateInstance(
                        DateFormat.MEDIUM,
                        Locale.getDefault()
                );
        var endDate = object.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant();

        return List.of(
                object.getId(),
                object.getOwnerUsername(),
                timeFormat.format(Date.from(object.getCreationDate().toInstant())),
                object.getName(),
                object.getSalary(),
                dateFormat.format(object.getStartDate()),
                dateFormat.format(Date.from(endDate)),
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
    protected List<String> getColumnNames() {
        return columnNameKeys
                .stream()
                .map(UIResources.getInstance()::get)
                .toList();
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        var isUnlocked = !lockedColumns.contains(getColumnName(column));
        var isOwned = getBaseModel().isEditableByCurrentUser(getBaseModel().get(row));
        return isUnlocked && isOwned;
    }
}

