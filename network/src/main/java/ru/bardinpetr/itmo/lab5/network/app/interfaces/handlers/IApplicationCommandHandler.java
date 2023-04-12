package ru.bardinpetr.itmo.lab5.network.app.interfaces.handlers;

import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * @param <S> request payload type
 * @param <R> response payload type
 */
public interface IApplicationCommandHandler<S extends IIdentifiableCommand, R> {
    void handle(AppRequest<S, R> request);
}
