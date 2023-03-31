package ru.bardinpetr.itmo.lab5.network.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;


/**
 * Low level message object to passed over channel
 */
@Data
@AllArgsConstructor
public class SocketMessage {
    private final CommandType cmdType;

    @With
    private final Long id;

    private final Long replyId;
    private final boolean continued;

    private byte[] payload;


    /**
     * @return prepared ACK message
     */
    public static SocketMessage ACK() {
        return new SocketMessage(CommandType.ACK, 0L, 0L, false, null);
    }

    /**
     * @return prepared NACK message
     */
    public static SocketMessage NACK() {
        return new SocketMessage(CommandType.NACK, 0L, 0L, false, null);
    }


    /**
     * @return true if this message is a DATA response for other DATA request
     */
    public boolean isResponse() {
        return cmdType == CommandType.DATA && replyId > 0;
    }

    /**
     * @return true if this message is a DATA request
     */
    public boolean isRequest() {
        return cmdType == CommandType.DATA && replyId <= 0;
    }

    public enum CommandType {
        INIT, HALT, ACK, NACK, DATA
    }
}
