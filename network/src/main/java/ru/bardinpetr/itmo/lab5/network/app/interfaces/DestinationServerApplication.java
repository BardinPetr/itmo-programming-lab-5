package ru.bardinpetr.itmo.lab5.network.app.interfaces;

import ru.bardinpetr.itmo.lab5.network.app.models.AppResponse;

public interface DestinationServerApplication<T> {
    void send(AppResponse<T> response);
}
