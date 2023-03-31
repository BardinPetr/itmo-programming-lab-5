package ru.bardinpetr.itmo.lab5.network.models;

import lombok.Data;

import java.io.Serializable;

/**
 * Low level message object to passed over channel
 */
@Data
public class SocketMessage implements Serializable {
    private static final int payloadSize = 1024;
    private final CommandType cmdType;
    private final long id;
    private final long replyId;
    private final boolean waitData;

    private final byte[] payload;

    public enum CommandType {
        INIT, HALT, ACK, NACK, DATA
    }

//    @Override
//    public String toString() {
//        return "SocketMessage{" +
//                "cmdType=" + cmdType +
//                ", id=" + id +
//                ", replyId=" + replyId +
//                ", waitData=" + waitData +
//                ", payload=" + new String(payload) +
//                '}';
//    }
}
