package ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import javax.swing.*;
import java.beans.PropertyChangeEvent;

public abstract class ResourcedPanel extends JPanel {

    private final UIResources uiResources;

    public ResourcedPanel() {
        uiResources = UIResources.getInstance();
        uiResources.addLocaleChangeListener(this::localeChange);
    }

    private void localeChange(PropertyChangeEvent propertyChangeEvent) {
        initComponentsI18n();
    }

    protected final UIResources getResources() {
        return uiResources;
    }

    protected final UIResources getUIResources() {
        return uiResources;
    }

    protected abstract void initComponentsI18n();
}
