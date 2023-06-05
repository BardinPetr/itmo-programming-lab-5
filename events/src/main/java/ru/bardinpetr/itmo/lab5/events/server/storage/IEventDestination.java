package ru.bardinpetr.itmo.lab5.events.server.storage;

import ru.bardinpetr.itmo.lab5.events.models.Event;

public interface IEventDestination {

    /**
     * Add event to storage
     */
    void insert(Event event);
}
