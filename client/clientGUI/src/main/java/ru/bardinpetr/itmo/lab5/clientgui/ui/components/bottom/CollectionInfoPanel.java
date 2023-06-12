package ru.bardinpetr.itmo.lab5.clientgui.ui.components.bottom;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames.ResourcedPanel;
import ru.bardinpetr.itmo.lab5.clientgui.ui.utils.GridConstrains;

import javax.swing.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CollectionInfoPanel extends ResourcedPanel {
    private JLabel infoText;
    private JLabel dbTypeNameLabel;
    private JLabel label2;
    private JLabel bdDateField;
    private JLabel label4;
    private JLabel workersCountField;


    public CollectionInfoPanel() {
        initComponents();
        setVisible(true);
    }

    protected void initComponents() {
        infoText = new JLabel();
        dbTypeNameLabel = new JLabel();
        label2 = new JLabel();
        bdDateField = new JLabel();
        label4 = new JLabel();
        workersCountField = new JLabel();

        var infoFont = new Font("italic", Font.PLAIN, 12);
        dbTypeNameLabel.setFont(infoFont);
        bdDateField.setFont(infoFont);
        workersCountField.setFont(infoFont);

        var infoConstrains = GridConstrains.normalAdd();

        var infoPanel = new JPanel(new GridBagLayout());
        infoPanel.add(infoText, infoConstrains);
        infoPanel.add(dbTypeNameLabel, infoConstrains);
        infoPanel.add(label2, infoConstrains);
        infoPanel.add(bdDateField, infoConstrains);
        infoPanel.add(label4, infoConstrains);
        infoPanel.add(workersCountField, infoConstrains);

        setLayout(new BorderLayout());
        add(infoPanel, BorderLayout.WEST);

        initComponentsI18n();
        setVisible(true);
    }

    public void setInitDate(ZonedDateTime date) {
        bdDateField.setText(
                DateTimeFormatter.ofPattern(
                        getResources().get("dateFormat")
                ).format(date));
    }

    public void setBDSize(Integer size) {
        workersCountField.setText(String.valueOf(size));
    }

    protected void initComponentsI18n() {
        UIResources resources = getResources();
        infoText.setText(resources.get("bottomPanel.infoText.text"));
        dbTypeNameLabel.setText(resources.get("bottomPanel.label1.text"));
        label2.setText(resources.get("bottomPanel.label2.text"));
        label4.setText(resources.get("bottomPanel.label4.text"));
    }
}
