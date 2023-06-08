package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.*;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.NullableDatePanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Coordinates;
import ru.bardinpetr.itmo.lab5.models.data.Organization;
import ru.bardinpetr.itmo.lab5.models.data.OrganizationType;
import ru.bardinpetr.itmo.lab5.models.data.Worker;
import ru.bardinpetr.itmo.lab5.models.data.validation.WorkerValidator;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class WorkerInfoPanelZ extends ResourcedPanel {
    private JLabel label1;
    private NameField workerNameField;
    private JLabel label2;
    private SalaryWorkerField workerSalaryField;
    private JLabel label3;
    private DateField workerStartField;
    private JLabel label4;
    private NullableDatePanel endDatePanel;
    private JLabel label5;
    private XCoordinateWorkerField workerXField;
    private JLabel label6;
    private YCoordinateWorkerField workerYField;
    private JLabel label7;
    private OrganizationIdBox organizationIdField;
    private JLabel label8;
    private PositionComboBox workerPositionCombobox;
    private final DataContainer<Worker> workerDataContainer;
    private Worker defaultWorker;
    ResourceBundle bundle = getResources();


    public WorkerInfoPanelZ(Worker defaultWorker) {
        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
        this.workerDataContainer = new DataContainer<>(
                new Worker(),
                (new WorkerValidator()).validateAll(new Worker())
        );
    }

    protected void initComponents(){
        label1 = new JLabel();
        label2 = new JLabel();

        workerNameField = new NameField((s)-> {});

        workerSalaryField = new SalaryWorkerField((s -> {}));

        label3 = new JLabel();
        workerStartField = new DateField((s -> {}));

        label4 = new JLabel();
        endDatePanel = new NullableDatePanel((s) -> {});
        label5 = new JLabel();
        workerXField = new XCoordinateWorkerField((s -> {}));
        label6 = new JLabel();
        workerYField = new YCoordinateWorkerField((s -> {}));
        label7 = new JLabel();

        organizationIdField = new OrganizationIdBox((e -> {}));

        label8 = new JLabel();

        workerPositionCombobox = new PositionComboBox((e -> {}));

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 300, 0};
        add(label1, GridConstrains.placedAdd(0,0));
        add(label2, GridConstrains.placedAdd(0, 1));
        add(label3, GridConstrains.placedAdd(0, 2));
        add(label4, GridConstrains.placedAdd(0, 3));
        add(label5, GridConstrains.placedAdd(0, 4));
        add(label6, GridConstrains.placedAdd(0, 5));
        add(label7, GridConstrains.placedAdd(0, 6));
        add(label8, GridConstrains.placedAdd(0, 7));

        add(workerNameField, GridConstrains.placedAdd(1, 0));
        add(workerSalaryField, GridConstrains.placedAdd(1, 1));
        add(workerStartField, GridConstrains.placedAdd(1, 2));
        add(endDatePanel, GridConstrains.placedAdd(1, 3));
        add(workerXField, GridConstrains.placedAdd(1, 4));
        add(workerYField, GridConstrains.placedAdd(1, 5));
        add(organizationIdField, GridConstrains.placedAdd(1, 6));
        add(workerPositionCombobox, GridConstrains.placedAdd(1, 7));

        if (defaultWorker!=null){
            workerNameField.setName(defaultWorker.getName());
            workerSalaryField.setData(defaultWorker.getSalary());
            workerStartField.setData(defaultWorker.getStartDate());
            endDatePanel.setData(defaultWorker.getEndDate());
            endDatePanel.setData(defaultWorker.getEndDate());
            workerXField.setData(defaultWorker.getCoordinates().getX());
            workerYField.setData(defaultWorker.getCoordinates().getY());
            workerPositionCombobox.setData(defaultWorker.getPosition());
            if (defaultWorker.getOrganization()!=null)
                organizationIdField.setData(defaultWorker.getOrganization().getId());
        }
        initComponentsI18n();
    }

    public DataContainer<Worker> getWorker(){
        DataContainer<Worker> dataContainer = new DataContainer(
                true,
                new Worker(),
                ""
                );
        var name = workerNameField.getData();
        var salary = workerSalaryField.getData();
        var startDate = workerStartField.getData();
        var endDate = endDatePanel.getData();
        var x = workerXField.getData();
        var y = workerYField.getData();
        var orgId = organizationIdField.getData();
        var position = workerPositionCombobox.getData();

        if (!name.isAllowed) dataContainer.copyMeta(name);
        if (!salary.isAllowed) dataContainer.copyMeta(salary);
        if (!startDate.isAllowed) dataContainer.copyMeta(startDate);
        if (!endDate.isAllowed) dataContainer.copyMeta(endDate);
        if (!x.isAllowed) dataContainer.copyMeta(x);
        if (!y.isAllowed) dataContainer.copyMeta(y);
        if (!orgId.isAllowed) dataContainer.copyMeta(orgId);
        if (!position.isAllowed) dataContainer.copyMeta(position);

        dataContainer.data.setName(name.data);
        dataContainer.data.setSalary(salary.data);
        dataContainer.data.setStartDate(startDate.data);
        dataContainer.data.setEndDate(endDate.data);
        dataContainer.data.setCoordinates(new Coordinates(
                x.data,
                y.data
        ));
        dataContainer.data.setOrganization(
                new Organization(orgId.data, "test", OrganizationType.COMMERCIAL));
        dataContainer.data.setPosition(position.data);
        return dataContainer;

    }




    @Override
    protected void initComponentsI18n() {
        bundle = getResources();

        label1.setText(bundle.getString("WorkerInfoPanel.label1.text"));
        label2.setText(bundle.getString("WorkerInfoPanel.label2.text"));
        label3.setText(bundle.getString("WorkerInfoPanel.label3.text"));
        label4.setText(bundle.getString("WorkerInfoPanel.label4.text"));
        label5.setText(bundle.getString("WorkerInfoPanel.label5.text"));
        label6.setText(bundle.getString("WorkerInfoPanel.label6.text"));
        label7.setText(bundle.getString("WorkerInfoPanel.label7.text"));
        label8.setText(bundle.getString("WorkerInfoPanel.label8.text"));

        organizationIdField.noSuchIdMsg = "OrganizationIdBox.validateValue.notFound.text";
    }

}
