package ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames;

import javax.swing.*;

public class ResourcedLabel extends ResourcedPanel{
    private JLabel label;
    private final String labelTextKey;
    private final String extraString;

    public ResourcedLabel(String labelTextKey) {
        this(labelTextKey, "");
    }

    public ResourcedLabel(String labelTextKey, String extraString) {
        this.extraString = extraString;
        this.labelTextKey = labelTextKey;
        initComponents();
        initComponentsI18n();
    }

    private void initComponents(){
        this.label = new JLabel();
        add(label);
    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        label.setText(resources.get(labelTextKey)+extraString);


    }
}
