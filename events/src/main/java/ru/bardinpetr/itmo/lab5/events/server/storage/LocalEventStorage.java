package ru.bardinpetr.itmo.lab5.events.server.storage;

import lombok.extern.slf4j.Slf4j;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.models.EventKey;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.time.Instant;
import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * EventStorage implementation based on tree structure in RAM
 */
@Slf4j
public class LocalEventStorage implements IEventStorage {
    private final NavigableMap<EventKey, Event> storage = new ConcurrentSkipListMap<>();
    private final AtomicInteger curId = new AtomicInteger(0);

    @Override
    public void insert(Event event) {
        event.setEventKey(EventKey.now(curId.incrementAndGet()));
        storage.put(event.getEventKey(), event);
        log.debug("New event registered: {}", event);
    }

    @Override
    public EventSet retrieve(Instant from, Instant to) {
        var events =
                storage
                        .subMap(EventKey.at(from), EventKey.at(to))
                        .values()
                        .stream().toList();
        return new EventSet(events, from, to);
    }
}
