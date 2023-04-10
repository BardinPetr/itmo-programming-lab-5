package ru.bardinpetr.itmo.lab5.network.app.interfaces;

import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * @param <K> request payload type
 * @param <R> response payload type
 */
public interface IApplicationCommandHandler<K extends IIdentifiableCommand, R> {
    void handle(AppRequest<K, R> request);
}
