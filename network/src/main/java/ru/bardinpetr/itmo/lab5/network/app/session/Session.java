package ru.bardinpetr.itmo.lab5.network.app.session;

import lombok.Data;

/**
 * Handles session with other party
 *
 * @param <K> type for object uniquely identifying target
 */
@Data
public class Session<K> {
    private final K address;
    private State state = State.CREATED;

    public enum State {
        CREATED,
        OPERATING,
        DEAD
    }
}
