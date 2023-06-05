package ru.bardinpetr.itmo.lab5.events.client.consumers;

import lombok.AllArgsConstructor;
import ru.bardinpetr.itmo.lab5.events.models.Event;
import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.util.function.Consumer;

@AllArgsConstructor
public class EventConsumer implements Consumer<EventSet> {
    protected Consumer<EventSet> target;

    /**
     * Check if event passes current filter
     *
     * @return true if ok
     */
    protected boolean check(Event event) {
        return true;
    }

    @Override
    public final void accept(EventSet events) {
        var filtered = events.getEvents().stream().filter(this::check).toList();
        target.accept(new EventSet(filtered, events.getTimestampStart(), events.getTimestampEnd()));
    }
}
