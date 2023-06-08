package ru.bardinpetr.itmo.lab5.clientgui.ui.components.checkedlist;

import lombok.Getter;

import javax.swing.*;
import java.util.Arrays;
import java.util.EventListener;
import java.util.EventObject;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CheckBoxListModel extends DefaultListModel<CheckBoxList.CheckedItem> {
    public void toggleChecked(int id) {
        setChecked(id, !getElementAt(id).isChecked());
    }

    public void setChecked(int id, boolean status) {
        getElementAt(id).setChecked(status);
        fireCheckedChanged(id);
    }

    public Stream<CheckBoxList.CheckedItem> getAll() {
        return Arrays
                .stream(this.toArray())
                .map(i -> ((CheckBoxList.CheckedItem) i));
    }

    public Set<Object> getChecked() {
        return getAll()
                .filter(CheckBoxList.CheckedItem::isChecked)
                .map(CheckBoxList.CheckedItem::getData)
                .collect(Collectors.toSet());
    }

    public void fireCheckedChanged(int id) {
        fireContentsChanged(this, id, id);
        var evt = new CheckedEvent(this, getElementAt(id));

        for (var i : listenerList.getListenerList())
            if (i instanceof CheckedListener chListener)
                chListener.checkedChanged(evt);
    }

    public void addCheckedEventListener(CheckedListener l) {
        listenerList.add(CheckedListener.class, l);
    }

    public void removeCheckedEventListener(CheckedListener l) {
        listenerList.add(CheckedListener.class, l);
    }

    public interface CheckedListener extends EventListener {
        void checkedChanged(CheckedEvent event);
    }

    public static class CheckedEvent extends EventObject {

        @Getter
        private final CheckBoxList.CheckedItem element;

        public CheckedEvent(Object source, CheckBoxList.CheckedItem element) {
            super(source);
            this.element = element;
        }
    }
}