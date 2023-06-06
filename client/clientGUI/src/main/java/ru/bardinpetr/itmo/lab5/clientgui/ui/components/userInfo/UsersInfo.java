/*
 * Created by JFormDesigner on Wed Jun 07 00:24:12 MSK 2023
 */

package ru.bardinpetr.itmo.lab5.clientgui.ui.components.userInfo;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * @author zam12
 */
public class UsersInfo extends ResourcedPanel {
    public UsersInfo() {
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        username = new JLabel();
        usernameField = new JLabel();
        workersCountText = new JLabel();
        workersCountField = new JLabel();

        //======== this ========
        setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.
        border.EmptyBorder(0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER
        ,javax.swing.border.TitledBorder.BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font
        .BOLD,12),java.awt.Color.red), getBorder())); addPropertyChangeListener(
        new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("\u0062order"
        .equals(e.getPropertyName()))throw new RuntimeException();}});
        setLayout(new GridLayout(2, 0));
        add(username);
        add(usernameField);
        add(workersCountText);
        add(workersCountField);

        initComponentsI18n();
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
        setVisible(true);
    }

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

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Artem
    private JLabel username;
    private JLabel usernameField;
    private JLabel workersCountText;
    private JLabel workersCountField;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
