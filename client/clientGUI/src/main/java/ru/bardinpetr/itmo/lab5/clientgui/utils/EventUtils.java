package ru.bardinpetr.itmo.lab5.clientgui.utils;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.util.Arrays;
import java.util.function.Consumer;

public class EventUtils {
    @SuppressWarnings("unchecked")
    public static <T> void fireAll(EventListenerList list, Class<T> eventClass, Consumer<T> fireHandler) {
        new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() {
                Arrays.stream(list.getListenerList())
                        .filter(i -> eventClass.isAssignableFrom(i.getClass()))
                        .map(i -> (T) i)
                        .forEach(fireHandler);
                return null;
            }
        }.execute();
    }
}
