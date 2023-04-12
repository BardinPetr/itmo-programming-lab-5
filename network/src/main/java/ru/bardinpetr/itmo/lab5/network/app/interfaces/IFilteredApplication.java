package ru.bardinpetr.itmo.lab5.network.app.interfaces;

import ru.bardinpetr.itmo.lab5.models.commands.requests.IIdentifiableCommand;
import ru.bardinpetr.itmo.lab5.network.app.models.AppRequest;

/**
 * Allows applications to filter incoming requests
 */
public interface IFilteredApplication<S extends IIdentifiableCommand, R> {

    /**
     * Check if request is appropriate for this app
     *
     * @param req input request
     * @return true if request is appropriate for this app
     */
    default boolean filter(AppRequest<S, R> req) {
        return true;
    }
}
