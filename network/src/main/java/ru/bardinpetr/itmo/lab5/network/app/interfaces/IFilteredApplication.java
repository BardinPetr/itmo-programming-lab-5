package ru.bardinpetr.itmo.lab5.network.app.interfaces;

import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * Allows applications to filter incoming requests
 */
public interface IFilteredApplication {

    /**
     * @param req input request
     * @param <S> request type
     * @param <R> response type
     * @return true if request is appropriate for this app
     */
    default <S extends IIdentifiableCommand, R> boolean filter(AppRequest<S, R> req) {
        return true;
    }
}
