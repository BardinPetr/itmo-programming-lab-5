package ru.bardinpetr.itmo.lab5.events.client;

import ru.bardinpetr.itmo.lab5.events.client.consumers.EventConsumer;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public abstract class AbstractEventSource {
    private final Set<EventConsumer> consumers = new HashSet<>();

    public void unsubscribe(EventConsumer consumer) {
        consumers.remove(consumer);
    }

    /**
     * Subscribe for specific event types
     */
    public void subscribe(EventConsumer consumer) {
        consumers.add(consumer);
    }

    /**
     * Subscribe for any events
     */
    public void subscribe(Consumer<EventSet> consumer) {
        subscribe(new EventConsumer(consumer));
    }

    protected void notifyListeners(EventSet data) {
        for (var i : consumers)
            i.accept(data);
    }

    public void stop() {
    }
}
