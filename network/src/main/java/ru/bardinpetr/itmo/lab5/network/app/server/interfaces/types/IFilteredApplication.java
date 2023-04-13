package ru.bardinpetr.itmo.lab5.network.app.server.interfaces.types;

import ru.bardinpetr.itmo.lab5.network.app.server.requests.AppRequest;

/**
 * Allows applications to filter incoming requests
 */
public interface IFilteredApplication {

    /**
     * Check if request is appropriate for this app
     *
     * @param req input request
     * @return true if request is appropriate for this app
     */
    default boolean filter(AppRequest req) {
        return true;
    }
}
