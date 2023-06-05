package ru.bardinpetr.itmo.lab5.clientgui.i18n;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UIResources {
    public static final String BASE_BUNDLE_NAME = "ru.bardinpetr.itmo.lab5.clientgui.i18n.data.GUITexts";
    public static final String LOCALE_PROPERTY = "locale";

    private static UIResources instance;

    private final PropertyChangeSupport propertyChangeSupport;
    private Locale currentLocale;

    private UIResources() {
        currentLocale = Locale.getDefault();
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public static UIResources getInstance() {
        if (instance == null) instance = new UIResources();
        return instance;
    }

    public String get(String key) {
        try {
            return getBundle().getString(key);
        } catch (MissingResourceException ignored) {
            return "Not supported locale";
        }
    }

    public ResourceBundle getBundle() {
        return ResourceBundle.getBundle(BASE_BUNDLE_NAME, currentLocale);
    }

    public void setLocale(Locale newLocale) {
        propertyChangeSupport.firePropertyChange(LOCALE_PROPERTY, currentLocale, newLocale);
        currentLocale = newLocale;
        Locale.setDefault(newLocale);
        ResourceBundle.clearCache();
    }

    public void addLocaleChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(LOCALE_PROPERTY, listener);
    }

    public void removeLocaleChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(LOCALE_PROPERTY, listener);
    }
}
