package ru.bardinpetr.itmo.lab5.network.session;

import lombok.Data;

/**
 * Handles session with other party
 *
 * @param <K> type for object uniquely identifying target
 */
@Data
public class Session<K> {
    private Long recipientMessageCounter = 0L;
    private Long selfMessageCounter = 0L;
    private K address;

    public void send() {
        selfMessageCounter++;
    }

//    public Long get
}
