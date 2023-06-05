package ru.bardinpetr.itmo.lab5.events.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Comparator;


@Data
@NoArgsConstructor
public class Event implements Comparable<Event> {
    private EventKey eventKey;
    private EventType action;
    private String resource;
    private String object;

    public Event(EventType action, String resource, String object) {
        this.resource = resource;
        this.action = action;
        this.object = object;
    }

    @Override
    public int compareTo(@NonNull Event other) {
        return Comparator
                .comparing(Event::getEventKey)
                .thenComparing(Event::getAction)
                .thenComparing(Event::getResource)
                .compare(this, other);
    }

    public enum EventType {
        CREATE, UPDATE, DELETE
    }
}
