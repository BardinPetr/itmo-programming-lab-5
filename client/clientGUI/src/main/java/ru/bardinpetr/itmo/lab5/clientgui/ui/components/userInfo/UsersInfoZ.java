package ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class UsersInfoZ extends ResourcedPanel {
    private JLabel username;
    private JLabel usernameField;
    private JLabel workersCountText;
    private JLabel workersCountField;
    public UsersInfoZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents(){
        username = new JLabel();
        usernameField = new JLabel();
        workersCountText = new JLabel();
        workersCountField = new JLabel();

        setLayout(new GridBagLayout());
        add(username, GridConstrains.placedAdd(0,0));
        add(usernameField, GridConstrains.placedAdd(1,0));
        add(workersCountText, GridConstrains.placedAdd(0,1));
        add(workersCountField, GridConstrains.placedAdd(1,1));

        initComponentsI18n();
        setVisible(true);
    }
    @Override
    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        username.setText(bundle.getString("userInfo.username.text"));
        usernameField.setText(bundle.getString("userInfo.usernameField.text"));
        workersCountText.setText(bundle.getString("userInfo.workersCountText.text"));
        workersCountField.setText(bundle.getString("userInfo.workersCountField.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }

}
