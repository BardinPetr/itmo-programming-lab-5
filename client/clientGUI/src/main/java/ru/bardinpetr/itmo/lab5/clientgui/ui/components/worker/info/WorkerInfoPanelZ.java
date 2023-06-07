package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class WorkerInfoPanelZ extends ResourcedPanel {
    private JLabel label1;
    private JTextField workerNameField;
    private JLabel label2;
    private JTextField workerSalaryField;
    private JLabel label3;
    private JSpinner workerStartDateField;
    private JLabel label4;
    private JSpinner workerEndDateField;
    private JLabel label5;
    private JTextField workerXField;
    private JLabel label6;
    private JTextField workerYField;
    private JLabel label7;
    private JTextField organizationIdField;
    private JLabel label8;
    private JComboBox workerPositionList;

    public WorkerInfoPanelZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        label1 = new JLabel();
        workerNameField = new JTextField();
        label2 = new JLabel();
        workerSalaryField = new JTextField();
        label3 = new JLabel();
        workerStartDateField = new JSpinner(
                new SpinnerDateModel(
                        new Date(),
                        null,
                        null,
                        Calendar.MONTH)
                    );
        label4 = new JLabel();
        workerEndDateField = new JSpinner(
                new SpinnerDateModel(
                        new Date(),
                        null,
                        null,
                        Calendar.MONTH)
        );
        label5 = new JLabel();
        workerXField = new JTextField();
        label6 = new JLabel();
        workerYField = new JTextField();
        label7 = new JLabel();
        organizationIdField = new JTextField();
        label8 = new JLabel();
        workerPositionList = new JComboBox();

        var dateFormat = "dd.MM.yyyy";

        workerStartDateField.setEditor(new JSpinner.DateEditor(workerStartDateField, dateFormat));
        workerEndDateField.setEditor(new JSpinner.DateEditor(workerStartDateField, dateFormat));

//        workerEndDateField.getModel().addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                System.out.println(((SpinnerDateModel) e.getSource()).getDate());
//            }
//        });

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
        add(workerStartDateField, GridConstrains.placedAdd(1, 2));
        add(workerEndDateField, GridConstrains.placedAdd(1, 3));
        add(workerXField, GridConstrains.placedAdd(1, 4));
        add(workerYField, GridConstrains.placedAdd(1, 5));
        add(organizationIdField, GridConstrains.placedAdd(1, 6));
        add(workerPositionList, GridConstrains.placedAdd(1, 7));

        initComponentsI18n();


    }

    @Override
    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        label1.setText(bundle.getString("WorkerInfoPanel.label1.text"));
        label2.setText(bundle.getString("WorkerInfoPanel.label2.text"));
        label3.setText(bundle.getString("WorkerInfoPanel.label3.text"));
        label4.setText(bundle.getString("WorkerInfoPanel.label4.text"));
        label5.setText(bundle.getString("WorkerInfoPanel.label5.text"));
        label6.setText(bundle.getString("WorkerInfoPanel.label6.text"));
        label7.setText(bundle.getString("WorkerInfoPanel.label7.text"));
        label8.setText(bundle.getString("WorkerInfoPanel.label8.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

}
