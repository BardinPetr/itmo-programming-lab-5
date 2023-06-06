package ru.bardinpetr.itmo.lab5.clientgui.ui.components.lang;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class LanguageChanger extends JPanel implements ActionListener {

    private final JComboBox<Locale> combobox;
    private final UIResources resources;

    public LanguageChanger() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        resources = UIResources.getInstance();

        combobox = new JComboBox<>();
        combobox.addActionListener(this);
        var renderer = new LocaleRenderer();
        combobox.setRenderer(renderer);

        add(combobox);

        for (var i : resources.getSupportedLocales())
            combobox.addItem(i);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var newLocale = (Locale) combobox.getSelectedItem();
        resources.setLocale(newLocale);
    }


    private static class LocaleRenderer extends JLabel implements ListCellRenderer<Locale> {

        @Override
        public Component getListCellRendererComponent(JList<? extends Locale> list, Locale value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getDisplayName());
            return this;
        }
    }
}
