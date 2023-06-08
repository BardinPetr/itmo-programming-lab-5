package ru.bardinpetr.itmo.lab5.clientgui.ui.components.worker.fields.interfaces;

import ru.bardinpetr.itmo.lab5.clientgui.i18n.UIResources;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Consumer;

public abstract class AbstractWorkerComboBox<T> extends JComboBox implements IDataStorage<T>{

    protected ResourceBundle bundle = getResources();
    Consumer<T> handler;

    protected HashMap<String, T> positionMap = new HashMap<>();
    public AbstractWorkerComboBox(Consumer<T> handler) {
        this.handler = handler;
        groupItems();
        addItemListener((e -> {
            if (e.getStateChange()== ItemEvent.DESELECTED) return;
            var item = e.getItem();
            if (item=="") handler.accept(null);
            else  handler.accept(positionMap.get(item));
        }));


    }

    protected abstract void groupItems();

    private ResourceBundle getResources(){
        return UIResources.getInstance().getBundle();
    }
    protected void initComponentsI18n() {
        bundle = getResources();
        removeAllItems();
        positionMap.clear();
        groupItems();
    }

    @Override
    public void setData(T data) {
        setSelectedItem(String.valueOf(data));
    }
}
