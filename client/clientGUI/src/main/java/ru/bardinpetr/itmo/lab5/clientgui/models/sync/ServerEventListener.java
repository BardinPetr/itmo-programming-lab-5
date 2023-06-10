package ru.bardinpetr.itmo.lab5.clientgui.models.sync;

import ru.bardinpetr.itmo.lab5.events.models.Event;

import java.util.EventListener;

public interface ServerEventListener extends EventListener {
    void onEvent(Event e);
}
