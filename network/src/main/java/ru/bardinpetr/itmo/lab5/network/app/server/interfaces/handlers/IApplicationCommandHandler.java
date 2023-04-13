package ru.bardinpetr.itmo.lab5.network.app.server.interfaces.handlers;

import ru.bardinpetr.itmo.lab5.network.app.server.requests.AppRequest;

/**
 * handler for api commands
 */
public interface IApplicationCommandHandler {
    void handle(AppRequest request);
}