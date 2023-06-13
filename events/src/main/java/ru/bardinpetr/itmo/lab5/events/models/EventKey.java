package ru.bardinpetr.itmo.lab5.events.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
import java.util.Comparator;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventKey implements Comparable<EventKey> {
    private String id;
    private Instant timestamp;

    public static EventKey at(Instant time) {
        return new EventKey(UUID.randomUUID().toString(), time);
    }

    public static EventKey now() {
        return at(Instant.now());
    }

    @Override
    public int compareTo(@NonNull EventKey other) {
        return Comparator
                .comparing(EventKey::getTimestamp)
                .thenComparing(EventKey::getId)
                .compare(this, other);
    }
}
