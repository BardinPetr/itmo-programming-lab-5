package ru.bardinpetr.itmo.lab5.network.models;

import lombok.Data;


/**
 * Low level message object to passed over channel
 */
@Data
public class SocketMessage {
    private final CommandType cmdType;
    private final long id;
    private final long replyId;
    private final boolean waitData;

    private byte[] payload;

    public enum CommandType {
        INIT, HALT, ACK, NACK, DATA
    }
}
