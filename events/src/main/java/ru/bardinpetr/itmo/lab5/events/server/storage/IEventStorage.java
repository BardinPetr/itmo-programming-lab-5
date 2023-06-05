package ru.bardinpetr.itmo.lab5.events.server.storage;

import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.time.Instant;

/**
 * Interface for interaction with event storage
 */
public interface IEventStorage extends IEventDestination {
    /**
     * Get all events in time range specified
     *
     * @param from start timestamp
     * @param to   end timestamp
     * @return EventSet for selected time range
     */
    EventSet retrieve(Instant from, Instant to);

    default EventSet retrieve(Instant from) {
        return retrieve(from, Instant.MAX);
    }

    /**
     * Get all stored events
     *
     * @return EventSet with all existed events
     */
    default EventSet retrieve() {
        return retrieve(Instant.MIN, Instant.MAX);
    }
}
