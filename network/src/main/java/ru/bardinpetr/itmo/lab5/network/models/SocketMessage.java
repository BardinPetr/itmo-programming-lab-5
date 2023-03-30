package ru.bardinpetr.itmo.lab5.network.models;

import lombok.Data;


@Data
public class SocketMessage {
    private final CommandType cmdType;
    private final long id;
    private final long replyId;

    private Object payload;

    public enum CommandType {
        INIT, HALT, ACK, NACK, DATA
    }
}
