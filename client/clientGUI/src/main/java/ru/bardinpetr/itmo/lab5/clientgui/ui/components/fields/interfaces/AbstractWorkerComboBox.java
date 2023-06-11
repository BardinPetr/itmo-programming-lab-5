package ru.bardinpetr.itmo.lab5.clientgui.ui.components.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;
import ru.bardinpetr.itmo.lab5.models.data.validation.ValidationResponse;
import ru.bardinpetr.itmo.lab5.models.validation.IValidator;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import static javax.swing.SwingUtilities.invokeLater;

public abstract class AbstractWorkerComboBox<T> extends JComboBox implements IDataStorage<T>{

    protected ResourceBundle bundle = getResources();
    Consumer<T> handler;


    public AbstractWorkerComboBox(Consumer<T> handler) {
        this.handler = handler;
        invokeLater(()->groupItems());
        addItemListener((e -> {
            if (e.getStateChange()== ItemEvent.DESELECTED) return;
            var item = (T) e.getItem();
            handler.accept(item);
        }));

        UIResources.getInstance().addLocaleChangeListener((i) -> initComponentsI18n());
    }
    @Override
    public String getText(){
        return getSelectedItem().toString();
    }

    protected abstract void groupItems();

    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
    protected void initComponentsI18n() {
        bundle = getResources();
        removeAllItems();
        groupItems();
    }

    protected abstract List<T> getList();
    @Override
    public void setData(T data) {
        setSelectedItem(data);
    }
}
