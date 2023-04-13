package ru.bardinpetr.itmo.lab5.network.app.server.special.impl;

import ru.bardinpetr.itmo.lab5.models.commands.responses.APICommandResponse;
import ru.bardinpetr.itmo.lab5.network.app.server.requests.AppRequest;

/**
 * Application for terminating any requests that was not terminated by other applications or requests that are invalid
 */
public class ErrorHandlingApplication extends APIApplication {
    @Override
    protected AppRequest process(AppRequest request) {
        var resp = request.response();
        if (resp.getStatus() == APICommandResponse.Status.UNPROCESSED)
            resp.status(APICommandResponse.Status.NOT_FOUND);
        else if (!resp.getStatus().isError())
            resp.status(APICommandResponse.Status.SERVER_ERROR);
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
