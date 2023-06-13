package ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames;

import javax.swing.*;

public class ResourcedAreaText extends ResourcedPanel {
    private JTextArea label;
    private final String labelTextKey;
    private final String extraString;

    public ResourcedAreaText(String labelTextKey) {
        this(labelTextKey, "");
    }

    public ResourcedAreaText(String labelTextKey, String extraString) {
        this.extraString = extraString;
        this.labelTextKey = labelTextKey;
        initComponents();
        initComponentsI18n();
    }

    private void initComponents(){
        this.label = new JTextArea();
        add(label);
    }

    @Override
    protected void initComponentsI18n() {
        var resources = getResources();
        label.setText(resources.get(labelTextKey)+extraString);
    }
}
