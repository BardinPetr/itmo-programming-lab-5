package ru.bardinpetr.itmo.lab5.network.app.models;

import ru.bardinpetr.itmo.lab5.models.commands.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.AppResponseController;
import ru.bardinpetr.itmo.lab5.network.session.models.Session;

/**
 * @param session
 * @param cmd
 * @param <T>     payload type
 * @param <R>     response type
 */
public record AppRequest<T extends IIdentifiableCommand, R>(Session session, AppResponseController<R> response, T cmd) {
}