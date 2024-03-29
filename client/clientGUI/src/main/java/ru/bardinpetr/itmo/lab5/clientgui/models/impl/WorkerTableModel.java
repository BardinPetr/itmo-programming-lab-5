package ru.bardinpetr.itmo.lab5.clientgui.models.impl;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.table.model.TableListModelAdapter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.ZoneId;
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
        var numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());
        String endDate;
        if (object.getEndDate() == null)
            endDate = UIResources.getInstance().get("WorkerInfoPanel.endDateNull.text");
        else
            endDate = dateFormat.format(Date.from(object.getEndDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));

        var org = new OrganizationPresenter(object.getOrganization()).toString();
        var position = new EnumPresenter<>(object.getPosition()).toString();

        return List.of(
                object.getId(),
                object.getOwnerUsername(),
                timeFormat.format(Date.from(object.getCreationDate().toInstant())),
                object.getName(),
                numberFormat.format(object.getSalary()),
                dateFormat.format(object.getStartDate()),
                endDate,
                numberFormat.format(object.getCoordinates().getX()),
                numberFormat.format(object.getCoordinates().getY()),
                org,
                position
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

