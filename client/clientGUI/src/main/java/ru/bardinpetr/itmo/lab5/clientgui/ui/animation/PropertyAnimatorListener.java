package ru.bardinpetr.itmo.lab5.clientgui.ui.animation;

import java.util.EventListener;

public interface PropertyAnimatorListener<T> extends EventListener {
    void update(T state, boolean running);
}
