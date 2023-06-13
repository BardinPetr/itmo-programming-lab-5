package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.function.Consumer;

import static javax.swing.SwingUtilities.invokeLater;

public abstract class AbstractWorkerComboBox<T> extends JComboBox implements IDataStorage<T> {

    protected UIResources resources = getResources();
    Consumer<T> handler;


    public AbstractWorkerComboBox(Consumer<T> handler) {
        this.handler = handler;
        groupItems();
        addItemListener((e -> {
            if (e.getStateChange() == ItemEvent.DESELECTED) return;
            var item = (T) e.getItem();
            handler.accept(item);
        }));

        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
    }

    @Override
    public String getText() {
        return getSelectedItem().toString();
    }

    protected abstract void groupItems();

    private UIResources getResources() {
        return UIResources.getInstance();
    }

    protected void initComponentsI18n() {
        resources = getResources();
        removeAllItems();
        groupItems();
    }

    protected abstract List<T> getList();

    @Override
    public void setData(T data) {
        setSelectedItem(data);
    }
}
