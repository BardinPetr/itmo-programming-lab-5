package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class WorkerInfoPanelZ extends ResourcedPanel {
    private JLabel label1;
    private JTextField workerNameField;
    private JLabel label2;
    private JTextField workerSalaryField;
    private JLabel label3;
    private JPanel workerStartDateField;
    private JLabel label4;
    private JPanel workerEndDateField;
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
        workerStartDateField = new JPanel();
        label4 = new JLabel();
        workerEndDateField = new JPanel();
        label5 = new JLabel();
        workerXField = new JTextField();
        label6 = new JLabel();
        workerYField = new JTextField();
        label7 = new JLabel();
        organizationIdField = new JTextField();
        label8 = new JLabel();
        workerPositionList = new JComboBox();

        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 300, 0};
        ((GridBagLayout)getLayout()).rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        ((GridBagLayout)getLayout()).columnWeights = new double[] {1.0, 1.0, 1.0};
        ((GridBagLayout)getLayout()).rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0E-4};
        add(label1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerNameField, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerSalaryField, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.EAST, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerEndDateField, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label5, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerXField, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label6, new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerYField, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label7, new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(organizationIdField, new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(label8, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));
        add(workerPositionList, new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(0, 0, 0, 0), 0, 0));

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
