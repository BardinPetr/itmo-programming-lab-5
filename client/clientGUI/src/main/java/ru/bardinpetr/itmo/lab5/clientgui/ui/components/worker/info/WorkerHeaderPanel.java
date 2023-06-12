package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;
import ru.bardinpetr.itmo.lab5.models.data.Worker;

import javax.swing.*;
import java.awt.*;

public class WorkerHeaderPanel extends ResourcedPanel {
    private final Worker defaultWorker;
    UIResources resources = getResources();
    private JLabel idLabel;
    private JTextField idField;
    private JLabel usernameLabel;
    private JTextField usernameField;

    public WorkerHeaderPanel(Worker defaultWorker) {

        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        idLabel = new JLabel();
        usernameLabel = new JLabel();

        idField = new JTextField();

        usernameField = new JTextField();


        setLayout(new GridBagLayout());
        ((GridBagLayout) getLayout()).columnWidths = new int[]{0, 300, 0};
        add(idLabel, GridConstrains.placedAdd(0, 0));
        add(usernameLabel, GridConstrains.placedAdd(0, 1));

        add(idField, GridConstrains.placedAdd(1, 0));
        add(usernameField, GridConstrains.placedAdd(1, 1));

        idField.setEditable(false);
        usernameField.setEditable(false);

        if (defaultWorker != null) {
            idField.setText(String.valueOf(defaultWorker.getId()));
            usernameField.setText(defaultWorker.getOwnerUsername());
        }
        initComponentsI18n();
    }


    @Override
    protected void initComponentsI18n() {
        resources = getResources();

        idLabel.setText(resources.get("WorkerHeaderPanel.idLabel.text"));
        usernameLabel.setText(resources.get("WorkerHeaderPanel.usernameLabel.text"));
    }

}
