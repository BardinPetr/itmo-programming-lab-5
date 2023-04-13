package ru.bardinpetr.itmo.lab5.network.server.session;

public enum Status {
    NETWORK(),
    IDLE(),
    READING(),
    READINGFINISHED(),
    EXECUTING(),
    SENDING(),
    HALT()
}
