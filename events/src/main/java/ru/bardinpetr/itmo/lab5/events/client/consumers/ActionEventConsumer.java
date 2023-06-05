package ru.bardinpetr.itmo.lab5.events.client.consumers;

import lombok.NonNull;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.util.function.Consumer;

public class ActionEventConsumer extends EventConsumer {
    private final Event.EventType action;

    public ActionEventConsumer(Consumer<EventSet> target, @NonNull Event.EventType action) {
        super(target);
        this.action = action;
    }

    @Override
    protected boolean check(Event event) {
        return !action.equals(event.getAction());
    }
}
