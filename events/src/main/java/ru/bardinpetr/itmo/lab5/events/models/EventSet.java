package ru.bardinpetr.itmo.lab5.events.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventSet {
    private List<Event> events = List.of();
    private Instant timestampStart;
    private Instant timestampEnd;
}
