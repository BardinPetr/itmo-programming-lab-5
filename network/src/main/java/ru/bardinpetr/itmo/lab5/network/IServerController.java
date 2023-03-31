package ru.bardinpetr.itmo.lab5.network;


import ru.bardinpetr.itmo.lab5.network.models.SocketMessage;

public interface IServerController {
    void send(SocketMessage msg);

    SocketMessage receive();

}