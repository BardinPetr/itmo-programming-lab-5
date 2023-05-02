package ru.bardinpetr.itmo.lab5.network.app.server.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Handles session with other party
 *
 * @param <K> type for object uniquely identifying target
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session<K> {
    private K address;
    private State state = State.CREATED;

    public Session(K address) {
        this.address = address;
    }

    public enum State {
        CREATED,
        OPERATING,
        DEAD
    }
}
