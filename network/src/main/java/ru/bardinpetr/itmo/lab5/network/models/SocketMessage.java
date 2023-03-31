package ru.bardinpetr.itmo.lab5.network.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Low level message object to passed over channel
 */
@Data
@AllArgsConstructor
public class SocketMessage implements Serializable {
    public static final int payloadSize = 1024;
    private CommandType cmdType;
    private Long id;
    private Long replyId;
    private boolean continued;
    private byte[] payload;

    public SocketMessage(CommandType type) {
        this(type, 0L, 0L, false, null);
    }

    public SocketMessage(CommandType type, Long id, Long replyId) {
        this(type, id, replyId, false, null);
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
