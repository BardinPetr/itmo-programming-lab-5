package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.*;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces.NullableDatePanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.utils.DataContainer;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.EnumPresenter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.presenters.OrganizationPresenter;
import ru.bardinpetr.itmo.lab5.models.data.*;
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
    private OrganizationCombobox organizationIdField;
    private JLabel label8;
    private PositionComboBox workerPositionCombobox;
    private DataContainer<Worker> workerDataContainer;
    private Worker defaultWorker;
    private boolean isChangeable;
    ResourceBundle bundle = getResources();

    public WorkerInfoPanelZ() {this(null, true);}
    public WorkerInfoPanelZ(Worker defaultWorker, boolean isChangeable) {
        this.isChangeable = isChangeable;
        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
        this.workerDataContainer = new DataContainer<>(
                defaultWorker,
                (new WorkerValidator()).validateAll(defaultWorker)
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

        organizationIdField = new OrganizationCombobox((e -> {}));

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

        workerNameField.setEditable(isChangeable);
        workerSalaryField.setEditable(isChangeable);
        workerStartField.setEditable(isChangeable);
        endDatePanel.setEditable(isChangeable);
        workerXField.setEditable(isChangeable);
        workerYField.setEditable(isChangeable);
        workerPositionCombobox.setEnabled(isChangeable);
        organizationIdField.setEnabled(isChangeable);

        if (defaultWorker!=null){
            workerNameField.setData(defaultWorker.getName());
            workerSalaryField.setData(defaultWorker.getSalary());
            workerStartField.setData(defaultWorker.getStartDate());
            endDatePanel.setData(defaultWorker.getEndDate());
            workerXField.setData(defaultWorker.getCoordinates().getX());
            workerYField.setData(defaultWorker.getCoordinates().getY());
            workerPositionCombobox.setData(new EnumPresenter<>(defaultWorker.getPosition()));
            if (defaultWorker.getOrganization()!=null)
                organizationIdField.setData(OrganizationPresenter.fromOrganization(defaultWorker.getOrganization()));
        }

        System.out.println(workerNameField.getText());
        System.out.println(workerSalaryField.getText());
        System.out.println(workerStartField.getText());
        System.out.println(endDatePanel.getText());
        System.out.println(workerXField.getText());
        System.out.println(workerYField.getText());
        System.out.println(workerPositionCombobox.getText());
        System.out.println(organizationIdField.getText());


        initComponentsI18n();
    }

    public DataContainer<Worker> getWorker(){
        if (defaultWorker==null)
            workerDataContainer = new DataContainer<>(
                    true,
                    new Worker(),
                    ""
            );
        else
            workerDataContainer = new DataContainer<>(
                    true,
                    defaultWorker,
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

        if (!name.isAllowed) workerDataContainer.copyMeta(name);
        if (!salary.isAllowed) workerDataContainer.copyMeta(salary);
        if (!startDate.isAllowed) workerDataContainer.copyMeta(startDate);
        if (!endDate.isAllowed) workerDataContainer.copyMeta(endDate);
        if (!x.isAllowed) workerDataContainer.copyMeta(x);
        if (!y.isAllowed) workerDataContainer.copyMeta(y);
        if (!orgId.isAllowed) workerDataContainer.copyMeta(orgId);
        if (!position.isAllowed) workerDataContainer.copyMeta(position);

        if (!workerDataContainer.isAllowed) {
            workerDataContainer.data = null;
            return workerDataContainer;
        }
        workerDataContainer.data.setName(name.data);
        workerDataContainer.data.setName(name.data);
        workerDataContainer.data.setSalary(salary.data);
        workerDataContainer.data.setStartDate(startDate.data);
        workerDataContainer.data.setEndDate(endDate.data);
        workerDataContainer.data.setCoordinates(new Coordinates(
                x.data,
                y.data
        ));
        if (orgId.data != null)
            workerDataContainer.data.setOrganization(
                    orgId.data.getOrganization());
        else
            workerDataContainer.data.setOrganization(null);
        workerDataContainer.data.setPosition((Position) position.data.getEnumData());
        return workerDataContainer;

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
    }

}
