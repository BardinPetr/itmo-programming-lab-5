package ru.bardinpetr.itmo.lab5.clientgui.ui.animation;

import lombok.Setter;
import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;

public abstract class PropertyAnimator<T> {

    private final EventListenerList listenerList = new EventListenerList();
    private final Timer timer;
    protected T startPoint;
    protected T endPoint;
    @Setter
    private PropertyUpdater<T> updateTask;
    private T currentStatus;

    public PropertyAnimator(int periodMillis) {
        timer = new Timer(periodMillis, this::update);
    }

    private void update(ActionEvent event) {
        currentStatus = updateTask.update(currentStatus, startPoint, endPoint);
        fireUpdateEvent();
        if (checkEnded(currentStatus)) {
            timer.stop();
        }
    }

    abstract protected boolean checkEnded(T currentStatus);

    abstract protected T update(T current);

    public void animate(T start, T end) {
        if (timer.isRunning()) {
            timer.stop();
            start = currentStatus;
        }
        currentStatus = start;
        startPoint = start;
        endPoint = end;
        timer.start();
    }

    @SuppressWarnings("unchecked")
    protected void fireUpdateEvent() {
        EventUtils.fireAll(
                listenerList,
                PropertyAnimatorListener.class,
                i -> i.update(i, timer.isRunning())
        );
    }

    public void addListener(PropertyAnimatorListener<T> listener) {
        listenerList.add(PropertyAnimatorListener.class, listener);
    }

    public void removeListener(PropertyAnimatorListener<T> listener) {
        listenerList.remove(PropertyAnimatorListener.class, listener);
    }


    public interface PropertyUpdater<T> {
        T update(T current, T from, T to);
    }
}
