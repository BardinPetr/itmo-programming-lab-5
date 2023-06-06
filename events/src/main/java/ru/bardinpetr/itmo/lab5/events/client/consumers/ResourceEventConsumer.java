package ru.bardinpetr.itmo.lab5.events.client.consumers;

import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.util.function.Consumer;

public class ResourceEventConsumer extends EventConsumer {
    private final String resource;

    public ResourceEventConsumer(Consumer<EventSet> target, @NonNull String resource) {
        super(target);
        this.resource = resource;
    }

    @Override
    protected boolean check(Event event) {
        return resource.equals(event.getResource());
    }
}
