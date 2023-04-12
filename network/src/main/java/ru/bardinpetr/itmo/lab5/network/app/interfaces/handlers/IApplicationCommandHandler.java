package ru.bardinpetr.itmo.lab5.network.app.interfaces.handlers;

import ru.bardinpetr.itmo.lab5.network.app.requests.AppRequest;

/**
 * handler for api commands
 */
public interface IApplicationCommandHandler {
    void handle(AppRequest request);
}
