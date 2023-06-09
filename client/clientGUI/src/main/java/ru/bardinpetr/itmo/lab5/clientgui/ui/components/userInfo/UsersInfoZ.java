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

    public void setUsername(String name){
        usernameField.setText(name);
    }

    public void setWorkersCount(Integer count){
        workersCountField.setText(String.valueOf(count));
    }
    @Override
    protected void initComponentsI18n() {
        ResourceBundle bundle = getResources();
        username.setText(bundle.getString("userInfo.username.text"));
        workersCountText.setText(bundle.getString("userInfo.workersCountText.text"));
    }

}
