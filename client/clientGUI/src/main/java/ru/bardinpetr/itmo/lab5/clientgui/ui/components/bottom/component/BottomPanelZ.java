package ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom.component;

import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang.LanguageChanger;

import javax.swing.*;
import java.awt.*;
import java.util.ResourceBundle;

public class BottomPanelZ extends ResourcedPanel {
    private JLabel infoText;
    private JLabel label1;
    private JLabel label2;
    private JLabel bdDateField;
    private JLabel label4;
    private JLabel workersCountField;
    private LanguageChanger langLayout;

    public BottomPanelZ() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        infoText = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        bdDateField = new JLabel();
        label4 = new JLabel();
        workersCountField = new JLabel();
        langLayout = new LanguageChanger();

        setLayout(new GridLayout());
        add(infoText);
        add(label1);
        add(label2);
        add(bdDateField);
        add(label4);
        add(workersCountField);
        add(langLayout);

        initComponentsI18n();
        setVisible(true);
    }

    protected void initComponentsI18n() {
        // JFormDesigner - Component i18n initialization - DO NOT MODIFY  //GEN-BEGIN:initI18n  @formatter:off
        // Generated using JFormDesigner Evaluation license - Artem
        ResourceBundle bundle = getResources();
        infoText.setText(bundle.getString("bottomPanel.infoText.text"));
        label1.setText(bundle.getString("bottomPanel.label1.text"));
        label2.setText(bundle.getString("bottomPanel.label2.text"));
        label4.setText(bundle.getString("bottomPanel.label4.text"));
        // JFormDesigner - End of component i18n initialization  //GEN-END:initI18n  @formatter:on
    }
}
