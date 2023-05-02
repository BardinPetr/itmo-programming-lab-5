package ru.bardinpetr.itmo.lab5.network.app.server.special.impl;

import ru.bardinpetr.itmo.lab5.models.commands.responses.APIResponseStatus;
import ru.bardinpetr.itmo.lab5.network.app.server.models.requests.AppRequest;

/**
 * Application for terminating any requests that was not terminated by other applications or requests that are invalid
 */
public class ErrorHandlingApplication extends APIApplication {
    @Override
    protected AppRequest process(AppRequest request) {
        var resp = request.response();
        if (resp.getStatus() == APIResponseStatus.UNPROCESSED)
            resp.status(APIResponseStatus.NOT_FOUND);
        else if (!resp.getStatus().isError())
            resp.status(APIResponseStatus.SERVER_ERROR);
        resp.send();
        return request;
    }

    /**
     * Allows requests only
     */
    @Override
    public boolean filter(AppRequest req) {
        return req.status() == AppRequest.ReqStatus.INVALID && !req.response().isTerminated();
    }
}
