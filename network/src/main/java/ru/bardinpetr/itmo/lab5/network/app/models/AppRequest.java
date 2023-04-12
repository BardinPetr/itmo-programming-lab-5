package ru.bardinpetr.itmo.lab5.network.app.models;

import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.AppResponseController;
import ru.bardinpetr.itmo.lab5.network.session.Session;

/**
 * @param session
 * @param payload
 * @param <T>     payload type
 * @param <R>     response type
 */
public record AppRequest<T extends IIdentifiableCommand, R>(
        ReqStatus status,
        Session session,
        AppResponseController<R> response,
        T payload) {
    public Long id() {
        return response == null ? -1 : response.getId();
    }

    public enum ReqStatus {
        INIT,
        SESSION_ASSIGNED,
        NORMAL
    }
}
