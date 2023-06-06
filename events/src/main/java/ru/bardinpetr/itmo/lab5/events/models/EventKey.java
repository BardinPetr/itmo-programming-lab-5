package ru.bardinpetr.itmo.lab5.events.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.Comparator;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventKey implements Comparable<EventKey> {
    private Integer id;
    private Instant timestamp;

    public static EventKey at(Instant time) {
        return new EventKey(0, time);
    }

    public static EventKey now(int id) {
        return new EventKey(id, Instant.now());
    }

    @Override
    public int compareTo(@NonNull EventKey other) {
        return Comparator
                .comparing(EventKey::getTimestamp)
                .thenComparing(EventKey::getId)
                .compare(this, other);
    }
}
