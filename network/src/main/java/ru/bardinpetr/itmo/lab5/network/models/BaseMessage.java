package ru.bardinpetr.itmo.lab5.network.models;

import lombok.Data;


@Data
public class BaseMessage {
    private final SocketMessage.CommandType cmdType;
    private final byte[] payload;

    /**
     * @return prepared ACK message
     */
    public static BaseMessage ACK() {
        return new BaseMessage(SocketMessage.CommandType.ACK, null);
    }

    /**
     * @return prepared NACK message
     */
    public static BaseMessage NACK() {
        return new BaseMessage(SocketMessage.CommandType.NACK, null);
    }
}
