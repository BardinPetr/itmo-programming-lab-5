package ru.bardinpetr.itmo.lab5.clientgui.ui.animation;

import ru.bardinpetr.itmo.lab5.clientgui.utils.EventUtils;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.event.ActionEvent;

public abstract class PropertyAnimator<T> {

    private final EventListenerList listenerList = new EventListenerList();
    private final Timer timer;
    protected T startPoint;
    protected T endPoint;
    private T currentStatus;

    public PropertyAnimator(int periodMillis) {
        timer = new Timer(periodMillis, this::update);
    }

    private void update(ActionEvent event) {
        currentStatus = update(currentStatus);
        System.out.println(currentStatus);
        fireUpdateEvent();
        if (checkEnded(currentStatus)) {
            timer.stop();
        }
    }

    abstract protected boolean checkEnded(T currentStatus);

    abstract protected T update(T current);

    public void setCurrentStatus(T status) {
        if (timer.isRunning()) return;
        currentStatus = status;
    }

    public final void animate(T end) {
        if (currentStatus == null)
            return;

        if (timer.isRunning())
            timer.stop();

        startPoint = currentStatus;
        endPoint = end;

        preconfigure();
        if (checkEnded(startPoint)) {
            fireUpdateEvent();
            return;
        }

        timer.start();
    }

    protected void preconfigure() {
    }

    @SuppressWarnings("unchecked")
    protected void fireUpdateEvent() {
        EventUtils.fireAll(
                listenerList,
                PropertyAnimatorListener.class,
                i -> i.update(currentStatus, timer.isRunning())
        );
    }

    public void addListener(PropertyAnimatorListener<T> listener) {
        listenerList.add(PropertyAnimatorListener.class, listener);
    }

    public void removeListener(PropertyAnimatorListener<T> listener) {
        listenerList.remove(PropertyAnimatorListener.class, listener);
    }
}
