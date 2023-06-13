package ru.bardinpetr.itmo.lab5.clientgui.ui.components.frames;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import javax.swing.*;
import java.util.Locale;

import static javax.swing.SwingUtilities.invokeLater;

public abstract class ResourcedFrame extends JFrame {

    private final UIResources uiResources;

    public ResourcedFrame() {
        uiResources = UIResources.getInstance();
        uiResources.addLocaleChangeListener(this::localeChange);
    }

    @Override
    public void dispose() {
        uiResources.removeLocaleChangeListener(this::localeChange);
        super.dispose();
    }

    private void localeChange(Locale newLocale) {
        invokeLater(this::initComponentsI18n);
    }

    protected final UIResources getResources() {
        return uiResources;
    }

    protected final UIResources getUIResources() {
        return uiResources;
    }

    protected abstract void initComponentsI18n();
}
