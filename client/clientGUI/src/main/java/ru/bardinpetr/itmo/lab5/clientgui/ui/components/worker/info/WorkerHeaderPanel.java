package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.info;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
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

public class WorkerHeaderPanel extends ResourcedPanel {
    private JLabel idLabel;
    private JTextField idField;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private Worker defaultWorker;

    UIResources resources = getResources();

    public WorkerHeaderPanel(Worker defaultWorker) {

        this.defaultWorker = defaultWorker;
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        idLabel = new JLabel();
        usernameLabel = new JLabel();

        idField = new JTextField();

        usernameField = new JTextField();


        setLayout(new GridBagLayout());
        ((GridBagLayout)getLayout()).columnWidths = new int[] {0, 300, 0};
        add(idLabel, GridConstrains.placedAdd(0,0));
        add(usernameLabel, GridConstrains.placedAdd(0, 1));

        add(idField, GridConstrains.placedAdd(1, 0));
        add(usernameField, GridConstrains.placedAdd(1, 1));

        idField.setEditable(false);
        usernameField.setEditable(false);

        if (defaultWorker!=null){
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
